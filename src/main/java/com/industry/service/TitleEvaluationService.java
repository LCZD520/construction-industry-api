package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.TitleEvaluationSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 职称评审表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
public interface TitleEvaluationService extends IService<TitleEvaluationDO> {

    /**
     * 插入职称评审信息
     *
     * @param titleEvaluation TitleEvaluationDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TitleEvaluationDO titleEvaluation);

    /**
     * 获取职称评审信息
     *
     * @param page ListPages<TitleEvaluationDO>
     * @return ListPages<TitleEvaluationDO>
     */
    ListPages<TitleEvaluationDO> getListTitleEvaluations(ListPages<TitleEvaluationDO> page);

    /**
     * 获取职称评审信息详情
     *
     * @param id 职称评审信息id
     * @return TitleEvaluationDO
     */
    TitleEvaluationDO getDetailById(Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<TitleAssessorDO> page
     * @param id   职称评审id
     * @return ListPages<TitleAssessorDO>
     */
    ListPages<TitleAssessorDO> getWaitAssessor(ListPages<TitleAssessorDO> page, Integer id);

    /**
     * 获取未选待评审人员
     *
     * @param page            ListPages<TitleAssessorDO> page
     * @param id              职称评审id
     * @param listSelectedIds List<Integer> 已选职称评审人员id
     * @return ListPages<TitleAssessorDO>
     */
    ListPages<TitleAssessorDO> getUnselectedWaitAssessor(ListPages<TitleAssessorDO> page, Integer id, List<Integer> listSelectedIds);

    /**
     * 下单
     *
     * @param id              职称评审id
     * @param listSelectedIds 评审人员
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity placeOrder(Integer id, List<Integer> listSelectedIds);

    /**
     * 更新备注
     *
     * @param request RemarksRequest
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateRemarks(RemarksRequest request);

    /**
     * 更新职称评审信息
     *
     * @param titleEvaluation TitleEvaluationDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateTitleEvaluation(TitleEvaluationDO titleEvaluation);

    /**
     * 条件获取职称评审记录
     *
     * @param page            ListPages<TitleEvaluationDO>
     * @param titleEvaluation 搜索条件
     * @return ListPages<TitleEvaluationDO>
     */
    ListPages<TitleEvaluationDO> listTitleEvaluations(ListPages<TitleEvaluationDO> page, TitleEvaluationSearch titleEvaluation);

    /**
     * 删除职称评审
     *
     * @param id 职称评审信息id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 恢复数据
     *
     * @param id 人才id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int recoveryById(Integer id);
}
