package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.search.LogisticsSearch;
import com.industry.mapper.LogisticsMapper;
import com.industry.service.LogisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后勤表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Slf4j
@Service
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, LogisticsDO> implements LogisticsService {

    private LogisticsMapper mapper;

    @Autowired
    public void setMapper(LogisticsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(LogisticsDO logisticsDO) {
        return mapper.insert(logisticsDO);
    }

    @Override
    public IPage<LogisticsDO> listLogistics(Page<LogisticsDO> page, Integer talentId) {
        return mapper.listTalentLogistics(page, talentId);
    }

    @Override
    public IPage<LogisticsDO> listLogistics(Page<LogisticsDO> page) {
        return mapper.listAllLogistics(page);
    }

    @Override
    public int deleteById(Integer id) {
        LogisticsDO logisticsDO = mapper.selectById(id);
        if (logisticsDO != null) {
            return mapper.deleteById(id);
        }
        return 0;
    }

    @Override
    public int updateStatusById(LogisticsDO logisticsDO) {
        LogisticsDO logistics = mapper.selectById(logisticsDO.getId());
        UpdateWrapper<LogisticsDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", logisticsDO.getId());
        Boolean confirm = logisticsDO.getConfirm();
        if (confirm) {
            updateWrapper.set("status", 2);
        } else {
            updateWrapper.set("status", 3);
        }
        updateWrapper.set("confirm_remark", logisticsDO.getConfirmRemark());
        updateWrapper.set("confirm_cost", logisticsDO.getConfirmCost());
        if (logistics != null) {
            logisticsDO.setStatus(2);
            return mapper.update(logisticsDO, updateWrapper);
        }
        return 0;
    }

    @Override
    public IPage<LogisticsDO> listEnterpriseLogistics(Page<LogisticsDO> page, Integer enterpriseId) {
        return mapper.listEnterpriseLogistics(page, enterpriseId);
    }

    @Override
    public IPage<LogisticsDO> listTalentLogistics(Page<LogisticsDO> page, Integer talentId) {
        log.info("talentId:{}", talentId);
        return mapper.listTalentLogistics(page, talentId);
    }

    @Override
    public ListPages<LogisticsDO> listByConditionPages(ListPages<LogisticsDO> page, LogisticsSearch search) {
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
        final List<LogisticsDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }
}
