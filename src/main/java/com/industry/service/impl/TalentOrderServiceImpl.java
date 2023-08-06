package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.search.TalentOrderSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.*;
import com.industry.service.OrderSelectedTalentService;
import com.industry.service.TalentOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 人才订单表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Slf4j
@Service
public class TalentOrderServiceImpl extends ServiceImpl<TalentOrderMapper, TalentOrderDO> implements TalentOrderService {

    public static final String SOURCE_ENTERPRISE = "enterprise";
    public static final String SOURCE_QUALIFICATION_AGENCY = "qualificationAgency";

    private ResultEntity result;

    public static final String PREFX = "QYRC";

    public static final String PREFX2 = "ZZDB";

    private OrderSelectedTalentService orderSelectedTalentService;

    private TalentOrderMapper mapper;

    private TalentMapper talentMapper;

    private EnterpriseMapper enterpriseMapper;

    private EnterpriseDemandMapper demandMapper;

    private TalentEntryMapper talentEntryMapper;

    private TalentEntryRecordMapper talentEntryRecordMapper;

    private QualificationAgencyMapper qualificationMapper;

    @Autowired
    public void setMapper(TalentOrderMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setOrderSelectedTalentService(OrderSelectedTalentService orderSelectedTalentService) {
        this.orderSelectedTalentService = orderSelectedTalentService;
    }

    @Autowired
    public void setTalentMapper(TalentMapper talentMapper) {
        this.talentMapper = talentMapper;
    }

    @Autowired
    public void setEnterpriseMapper(EnterpriseMapper enterpriseMapper) {
        this.enterpriseMapper = enterpriseMapper;
    }

    @Autowired
    public void setDemandMapper(EnterpriseDemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    @Autowired
    public void setTalentEntryMapper(TalentEntryMapper talentEntryMapper) {
        this.talentEntryMapper = talentEntryMapper;
    }

    @Autowired
    public void setTalentEntryRecordMapper(TalentEntryRecordMapper talentEntryRecordMapper) {
        this.talentEntryRecordMapper = talentEntryRecordMapper;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setQualificationMapper(QualificationAgencyMapper qualificationMapper) {
        this.qualificationMapper = qualificationMapper;
    }

    @Override
    public int insert(TalentOrderDO talentOrder) {
        synchronized (this) {
            talentOrder.setOrderno(generateOrderno());
            talentOrder.setSource("enterprise");
            int insert = mapper.insert(talentOrder);
            if (insert > 0) {
                Integer id = talentOrder.getId();
                List<OrderSelectedTalentDO> selectedTalents
                        = talentOrder.getSelectedTalents();
                List<Integer> listTalentIds = new ArrayList<>();
                if (selectedTalents != null) {
                    selectedTalents.forEach(item -> {
                        item.setTalentOrderId(id);
                        item.setType("enterprise");
                        item.setContractBalance(item.getEnterpriseOffer());
                        listTalentIds.add(item.getTalentId());
                    });
                    talentMapper.updateTalentStatusBatch(listTalentIds);
                    enterpriseMapper.updateEnterpriseStatus(talentOrder.getEnterpriseId());
                    orderSelectedTalentService.saveBatch(selectedTalents);
                }

            }
            return insert;
        }
    }

    @Override
    public ListPages<TalentOrderDO> listTalentOrders(Integer id, ListPages<TalentOrderDO> page) {
        List<TalentOrderDO> listTalentOrders
                = mapper.listTalentOrders(id, page);
        page.setList(listTalentOrders);
        return page;
    }

    @Override
    public ListPages<TalentOrderDO> listAllTalentOrders(ListPages<TalentOrderDO> page) {
        List<TalentOrderDO> listTalentOrders
                = mapper.listAllTalentOrders(page);
        page.setTotal(mapper.getCountListAllOrders());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        page.setList(listTalentOrders);
        return page;
    }

    @Override
    public ResultEntity insertQualificationAgencyOrder(TalentOrderDO talentOrder) {
        synchronized (this) {
            List<OrderSelectedTalentDO> selectedTalents = talentOrder.getSelectedTalents();
            List<Integer> listTalentIds = selectedTalents.stream()
                    .map(OrderSelectedTalentDO::getTalentId).collect(Collectors.toList());
            final List<String> orderedTalents = talentMapper.getOrderedTalentIds(listTalentIds);
            if (!orderedTalents.isEmpty()) {
                return result.failure(ResultCodeEnum.FAIL_ORDER_EXISTED_TALENT, orderedTalents);
            }
            final List<Integer> canOrderTalentIds = talentMapper.getCanOrderTalentIds();
            if (canOrderTalentIds.containsAll(listTalentIds)) {
                talentOrder.setOrderno(generateOrderno2());
                mapper.insert(talentOrder);
                Integer id = talentOrder.getId();
                selectedTalents.forEach(item -> {
                    item.setTalentOrderId(id);
                    item.setContractBalance(item.getEnterpriseOffer());
                });
                talentMapper.updateTalentStatusBatch(listTalentIds);
                qualificationMapper.updateStatusById(2, talentOrder.getEnterpriseId());
                orderSelectedTalentService.saveBatch(selectedTalents);
                return result.success(ResultCodeEnum.SUCCESS_PLACE_ORDER);
            } else {
                // 不存在的下单人员
                List<Integer> notExistTalentIds =
                        listTalentIds.stream().filter(item ->
                                !canOrderTalentIds.contains(item)).collect(Collectors.toList());
                List<String> notExistTalentFullNames = new ArrayList<>();
                for (Integer item : notExistTalentIds) {
                    final List<String> collect = selectedTalents.stream()
                            .filter(talent -> talent.getId().equals(item))
                            .collect(Collectors.toList()).stream()
                            .map(OrderSelectedTalentDO::getFullName)
                            .collect(Collectors.toList());
                    notExistTalentFullNames.addAll(collect);
                }
                return result.failure(ResultCodeEnum.FAIL_ORDER_NOT_EXIST_TALENT, notExistTalentFullNames);
            }
        }
    }

    @Override
    public ListPages<TalentOrderDO> listByConditionPages(ListPages<TalentOrderDO> page, TalentOrderSearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (!StringUtils.isEmpty(startDateStr)) {
            final LocalDateTime startTime = LocalDateTimeUtil.of(DateUtil.parse(startDateStr));
            final String newStartTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.beginOfDay(startTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setStartDate(newStartTimeStr);
        }
        if (!StringUtils.isEmpty(endDateStr)) {
            final LocalDateTime endTime = LocalDateTimeUtil.of(DateUtil.parse(endDateStr));
            final String newEndTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.endOfDay(endTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setEndDate(newEndTimeStr);
        }
        final List<TalentOrderDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public TalentOrderDO getDetailById(Integer id) {
        TalentOrderDO detailById = mapper.getDetailById(id);
        if (detailById != null) {
            List<Integer> integers
                    = JSONUtil.toList(detailById.getEnterpriseDemandIds(), Integer.class);
            detailById.setEnterpriseDemands(demandMapper.selectBatchIds(integers));
        }
        return detailById;
    }

    @Override
    public List<OrderSelectedTalentDO> getListSelectedTalentsById(Integer id) {
        return mapper.getListSelectedTalentsById(id);
    }

    @Override
    public boolean mergeOrder(Integer id, String source) {
        synchronized (this) {
            TalentOrderDO maxTalentOrder
                    = mapper.getMaxOrdernoByEnterpriseId(id, source);
            Integer maxOrderStatus
                    = mapper.getMaxOrderStatusByEnterpriseId(id, source);
            Integer originalTalentOrderId = maxTalentOrder.getId();
            maxTalentOrder.setOrderStatus(maxOrderStatus);
            if (SOURCE_ENTERPRISE.equals(source)) {
                maxTalentOrder.setOrderno(generateOrderno());
            }
            if (SOURCE_QUALIFICATION_AGENCY.equals(source)) {
                maxTalentOrder.setOrderno(generateOrderno2());
            }
            log.info("maxTalentOrder:{}", maxTalentOrder);
            List<Integer> listTalentOrderIds
                    = mapper.getListTalentOrderIdsByEnterpriseId(id, source);
            int i2 = mapper.deleteBatchIds(listTalentOrderIds);
            int i = mapper.insertTalentOrder(maxTalentOrder);
            Integer talentOrderId = maxTalentOrder.getId();
            int i1 = mapper.updateTalentOrderId(
                    talentOrderId, listTalentOrderIds);
            talentEntryMapper.updateTalentOrderId(talentOrderId, originalTalentOrderId);
            talentEntryRecordMapper.updateTalentOrderId(talentOrderId, originalTalentOrderId);
            return i > 0 && i1 > 0 && i2 > 0;
        }
    }

    @Override
    public int deleteTalentOrderById(Integer id) {
        TalentOrderDO talentOrder = mapper.selectById(id);
        if (talentOrder != null) {
            int rows = mapper.deleteById(id);

            return rows;
        }
        return -1;
    }

    public synchronized String generateOrderno() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyy-MM-dd");
        String maxOrderno = mapper.getMaxOrderno(format);
        if (maxOrderno != null) {
            String prefix = maxOrderno.substring(0, 12);
            int i = Integer.parseInt(maxOrderno.substring(12));
            String newOrdernoSuffix = String.valueOf(i + 1);
            return prefix + String.format(
                    "%4s", newOrdernoSuffix).replace(" ", "0");
        }
        String format2 = DateUtil.format(parse, "yyyyMMdd");
        return PREFX + format2 + "0001";
    }

    public synchronized String generateOrderno2() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyy-MM-dd");
        String maxOrderno = mapper.getMaxOrderno2(format);
        if (maxOrderno != null) {
            String prefix = maxOrderno.substring(0, 12);
            int i = Integer.parseInt(maxOrderno.substring(12));
            String newOrdernoSuffix = String.valueOf(i + 1);
            return prefix + String.format(
                    "%4s", newOrdernoSuffix).replace(" ", "0");
        }
        String format2 = DateUtil.format(parse, "yyyyMMdd");
        return PREFX2 + format2 + "0001";
    }
}
