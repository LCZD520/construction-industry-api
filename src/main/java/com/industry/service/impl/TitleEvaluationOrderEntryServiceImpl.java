package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleEvaluationOrderEntryDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import com.industry.bean.search.TitleEvaluationOrderEntrySearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.TitleEvaluationOrderEntryMapper;
import com.industry.service.TitleEvaluationOrderEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 职称评审订单入账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
@Service
public class TitleEvaluationOrderEntryServiceImpl extends ServiceImpl<TitleEvaluationOrderEntryMapper, TitleEvaluationOrderEntryDO> implements TitleEvaluationOrderEntryService {

    public static final String DEFAULT_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private TitleEvaluationOrderEntryMapper mapper;

    @Autowired
    public void setMapper(TitleEvaluationOrderEntryMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Override
    public int insert(TitleEvaluationOrderEntryDO titleEvaluationOrderEntry) {
        return mapper.insert(titleEvaluationOrderEntry);
    }

    @Override
    public List<TitleEvaluationOrderEntryDO> getEntryRecordsByOrderId(Integer id) {
        return mapper.getEntryRecordsByOrderId(id);
    }

    @Override
    public ListPages<TitleEvaluationOrderEntryDO> listByConditionPages(ListPages<TitleEvaluationOrderEntryDO> page, TitleEvaluationOrderEntrySearch search) {
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
        final List<TitleEvaluationOrderEntryDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public TitleEvaluationOrderEntryDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public ResultEntity audit(ApprovalOpinionRequest request, Integer id) {
        synchronized (this) {
            final TitleEvaluationOrderEntryDO entry = mapper.selectById(id);
            if (entry == null) {
                return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_RECORD);
            }
            if (DEFAULT_APPLICATION_STATUS.equals(entry.getApplicationStatus())) {
                if (request.getAdopt()) {
                    mapper.updateStatusById("财务审批通过", id);
                } else {
                    mapper.updateStatusById("财务审批不通过", id);
                }
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
        }
    }
}
