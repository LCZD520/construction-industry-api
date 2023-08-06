package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TitleEvaluationOrderEntryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.TitleEvaluationOrderEntrySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 职称评审订单入账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
@Mapper
public interface TitleEvaluationOrderEntryMapper extends BaseMapper<TitleEvaluationOrderEntryDO> {

    /**
     * 根据职称评审订单id获取职称评审入账记录
     *
     * @param id 职称评审订单id
     * @return List<TitleEvaluationOrderEntryDO>
     */
    List<TitleEvaluationOrderEntryDO> getEntryRecordsByOrderId(@Param("id") Integer id);

    /**
     * 条件分页获取职称评审订单入账列表
     *
     * @param page   ListPages<TitleEvaluationOrderEntryDO>
     * @param search TitleEvaluationOrderEntrySearch
     * @return List<TitleEvaluationOrderEntryDO>
     */
    List<TitleEvaluationOrderEntryDO> listByConditionPages(
            @Param("page") ListPages<TitleEvaluationOrderEntryDO> page,
            @Param("search") TitleEvaluationOrderEntrySearch search);

    /**
     * 条件分页获取职称评审订单入账列表总数
     *
     * @param search TitleEvaluationOrderEntrySearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TitleEvaluationOrderEntrySearch search);

    /**
     * 获取职称评审订单入账详情
     *
     * @param id 职称评审订单入账id
     * @return TitleEvaluationOrderEntryDO
     */
    TitleEvaluationOrderEntryDO getDetailById(@Param("id") Integer id);

    /**
     * 更新审批状态
     *
     * @param status 更新的审批状态
     * @return 受影响rows
     */
    int updateStatusById(@Param("status") String status, @Param("id") Integer id);
}
