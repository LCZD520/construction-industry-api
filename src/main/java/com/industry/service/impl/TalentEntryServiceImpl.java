package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.*;
import com.industry.bean.search.TalentEntrySearch;
import com.industry.mapper.OrderSelectedTalentMapper;
import com.industry.mapper.TalentEntryMapper;
import com.industry.mapper.TalentEntryRecordMapper;
import com.industry.service.TalentEntryRecordService;
import com.industry.service.TalentEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人才入账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Slf4j
@Service
public class TalentEntryServiceImpl extends ServiceImpl<TalentEntryMapper, TalentEntryDO> implements TalentEntryService {

    private TalentEntryRecordService recordService;

    private TalentEntryMapper mapper;

    private TalentEntryRecordMapper recordMapper;

    private OrderSelectedTalentMapper orderSelectedTalentMapper;

    @Autowired
    public void setRecordService(TalentEntryRecordService recordService) {
        this.recordService = recordService;
    }

    @Autowired
    public void setMapper(TalentEntryMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setRecordMapper(TalentEntryRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Autowired
    public void setOrderSelectedTalentMapper(OrderSelectedTalentMapper orderSelectedTalentMapper) {
        this.orderSelectedTalentMapper = orderSelectedTalentMapper;
    }

    @Override
    public int insert(TalentEntryDO talentEntry) {
        int i = mapper.insert(talentEntry);
        Integer talentEntryId = talentEntry.getId();
        List<TalentEntryRecordDO> listEntrys
                = talentEntry.getListEntrys();
        List<TalentEntryRecordDO> listNotExists = new ArrayList<>();
        List<TalentEntryRecordDO> listExists = new ArrayList<>();
        if (!listEntrys.isEmpty()) {
            listEntrys.forEach(item -> {
                if (item.getId() == null) {
                    item.setTalentEntryId(talentEntryId);
                    item.setContractBalance(
                            item.getContractBalance() - item.getEntryAmount());
                    listNotExists.add(item);
                } else {
                    listExists.add(item);
                }
            });
        }
//        recordMapper.insertBatch(listNotExists);
        log.info("listNotExists:{}", listNotExists);
        if (!listNotExists.isEmpty()) {
            listNotExists.forEach(item -> {
                Integer contractBalance = item.getContractBalance();
                Integer talentId = item.getTalentId();
                OrderSelectedTalentDO talent
                        = orderSelectedTalentMapper.getByTalentId(talentId);
                if (talent != null) {
                    orderSelectedTalentMapper
                            .updateContractBalanceByTalentId(talentId, contractBalance);
                }
            });
        }
        recordService.saveBatch(listNotExists);
        return i;
    }

    @Override
    public ListPages<TalentEntryRecordDO> listTalentEntryByConditionPages(ListPages<TalentEntryRecordDO> page, TalentEntrySearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (startDateStr != null && !StringUtils.isEmpty(startDateStr)) {
            final LocalDateTime startTime = LocalDateTimeUtil.of(DateUtil.parse(startDateStr));
            final String newStartTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.beginOfDay(startTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setStartDate(newStartTimeStr);
        }
        if (endDateStr != null && !StringUtils.isEmpty(endDateStr)) {
            final LocalDateTime endTime = LocalDateTimeUtil.of(DateUtil.parse(endDateStr));
            final String newEndTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.endOfDay(endTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setEndDate(newEndTimeStr);
        }
        log.info("search:{}", search);
        final List<TalentEntryRecordDO> list = mapper.listTalentEntryByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }
}
