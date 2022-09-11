package com.industry.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.mapper.*;
import com.industry.service.OrderSelectedTalentService;
import com.industry.service.TalentOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static final String PREFX = "QYRC";

    private OrderSelectedTalentService orderSelectedTalentService;

    private TalentOrderMapper mapper;

    private TalentMapper talentMapper;

    private EnterpriseMapper enterpriseMapper;

    private EnterpriseDemandMapper demandMapper;

    private TalentEntryMapper talentEntryMapper;

    private TalentEntryRecordMapper talentEntryRecordMapper;

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

    @Override
    public int insert(TalentOrderDO talentOrder) {
        talentOrder.setOrderno(generateOrderno());
        int insert = mapper.insert(talentOrder);
        if (insert > 0) {
            Integer id = talentOrder.getId();
            List<OrderSelectedTalentDO> selectedTalents
                    = talentOrder.getSelectedTalents();
            List<Integer> listTalentIds = new ArrayList<>();
            if (selectedTalents != null) {
                selectedTalents.forEach(item -> {
                    item.setTalentOrderId(id);
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

    @Override
    public ListPages<TalentOrderDO> listTalentOrders(Integer id, ListPages<TalentOrderDO> page) {
        List<TalentOrderDO> listTalentOrders
                = mapper.listTalentOrders(id, page);
        page.setList(listTalentOrders);
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
    public boolean mergeOrder(Integer id) {
        TalentOrderDO maxTalentOrder
                = mapper.getMaxOrdernoByEnterpriseId(id);
        Integer maxOrderStatus
                = mapper.getMaxOrderStatusByEnterpriseId(id);
        Integer originalTalentOrderId = maxTalentOrder.getId();
        maxTalentOrder.setOrderStatus(maxOrderStatus);
        maxTalentOrder.setOrderno(generateOrderno());
        List<Integer> listTalentOrderIds
                = mapper.getListTalentOrderIdsByEnterpriseId(id);
        int i2 = mapper.deleteBatchIds(listTalentOrderIds);
        int i = mapper.insertTalentOrder(maxTalentOrder);
        Integer talentOrderId = maxTalentOrder.getId();
        int i1 = mapper.updateTalentOrderId(
                talentOrderId, listTalentOrderIds);
        talentEntryMapper.updateTalentOrderId(talentOrderId, originalTalentOrderId);
        talentEntryRecordMapper.updateTalentOrderId(talentOrderId, originalTalentOrderId);
        return i > 0 && i1 > 0 && i2 > 0;
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
}
