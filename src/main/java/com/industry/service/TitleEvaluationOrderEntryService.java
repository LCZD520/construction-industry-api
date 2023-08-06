package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleEvaluationOrderEntryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.ApprovalOpinionRequest;
import com.industry.bean.search.TitleEvaluationOrderEntrySearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 职称评审订单入账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
public interface TitleEvaluationOrderEntryService extends IService<TitleEvaluationOrderEntryDO> {

    /**
     * 插入职称评审订单
     *
     * @param titleEvaluationOrderEntry TitleEvaluationOrderEntryDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TitleEvaluationOrderEntryDO titleEvaluationOrderEntry);

    /**
     * 根据订单id获取职称入账记录
     *
     * @param id 职称评审订单id
     * @return List<TitleEvaluationOrderEntryDO>
     */
    List<TitleEvaluationOrderEntryDO> getEntryRecordsByOrderId(Integer id);

    /**
     * 条件分页获取职称评审订单入账列表
     *
     * @param page   ListPages<TitleEvaluationOrderEntryDO> page
     * @param search TitleEvaluationOrderEntrySearch
     * @return ListPages<TitleEvaluationOrderEntryDO>
     */
    ListPages<TitleEvaluationOrderEntryDO> listByConditionPages(ListPages<TitleEvaluationOrderEntryDO> page, TitleEvaluationOrderEntrySearch search);

    /**
     * 获取职称评审订单入账详情
     *
     * @param id 职称评审订单入账id
     * @return TitleEvaluationOrderEntryDO
     */
    TitleEvaluationOrderEntryDO getDetailById(Integer id);

    /**
     * 职称评审订单入账审批
     *
     * @param request ApprovalOpinionRequest
     * @param id      职称评审订单入账id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity audit(ApprovalOpinionRequest request, Integer id);
}
