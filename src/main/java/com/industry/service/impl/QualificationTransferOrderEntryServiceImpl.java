package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.industry.bean.search.QualificationTransferOrderEntrySearch;
import com.industry.mapper.QualificationTransferOrderEntryMapper;
import com.industry.service.QualificationTransferOrderEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资质转让订单入账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Service
public class QualificationTransferOrderEntryServiceImpl extends ServiceImpl<QualificationTransferOrderEntryMapper, QualificationTransferOrderEntryDO> implements QualificationTransferOrderEntryService {

    private QualificationTransferOrderEntryMapper mapper;

    @Autowired
    public void setMapper(QualificationTransferOrderEntryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationTransferOrderEntryDO entry) {
        synchronized (this) {
            return mapper.insert(entry);
        }
    }

    @Override
    public ListPages<QualificationTransferOrderEntryDO> listByConditionPages(ListPages<QualificationTransferOrderEntryDO> page, QualificationTransferOrderEntrySearch search) {
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
        final List<QualificationTransferOrderEntryDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public List<QualificationTransferOrderEntryDO> listByQualificationTransferOrderId(Integer id) {
        return mapper.listByQualificationTransferOrderId(id);
    }

}
