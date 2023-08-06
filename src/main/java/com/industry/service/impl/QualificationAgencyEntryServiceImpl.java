package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.entity.QualificationAgencyEntryDO;
import com.industry.bean.search.QualificationAgencyEntrySearch;
import com.industry.mapper.QualificationAgencyEntryMapper;
import com.industry.mapper.QualificationAgencyMapper;
import com.industry.service.QualificationAgencyEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资质代办入账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-02-26
 */
@Slf4j
@Service
public class QualificationAgencyEntryServiceImpl extends ServiceImpl<QualificationAgencyEntryMapper, QualificationAgencyEntryDO> implements QualificationAgencyEntryService {

    private QualificationAgencyEntryMapper mapper;

    private QualificationAgencyMapper agencyMapper;

    @Autowired
    public void setMapper(QualificationAgencyEntryMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setAgencyMapper(QualificationAgencyMapper agencyMapper) {
        this.agencyMapper = agencyMapper;
    }

    @Override
    public int insert(QualificationAgencyEntryDO qualificationAgencyEntryDO) {
        synchronized (this) {
            final int row = mapper.insertQualificationAgencyEntry(qualificationAgencyEntryDO);
            agencyMapper.updateStatusById(2, qualificationAgencyEntryDO.getAgencyCompanyId());
            return row;
        }
    }

    @Override
    public List<QualificationAgencyEntryDO> getList(Integer id) {
        return mapper.getList(id);
    }

    @Override
    public ListPages<QualificationAgencyEntryDO> listByConditionPages(ListPages<QualificationAgencyEntryDO> page, QualificationAgencyEntrySearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (startDateStr != null && !StringUtils.isEmpty(endDateStr)) {
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
        final List<QualificationAgencyEntryDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public QualificationAgencyEntryDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }
}
