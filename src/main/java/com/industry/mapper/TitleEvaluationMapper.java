package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.TitleEvaluationSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 职称评审表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface TitleEvaluationMapper extends BaseMapper<TitleEvaluationDO> {

    /**
     * 插入职称评审信息
     *
     * @param titleEvaluation TitleEvaluationDO
     * @return 受影响rows
     */
    int insertTitleEvaluation(TitleEvaluationDO titleEvaluation);

    /**
     * 职称评审信息总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取职称评审信息
     *
     * @param page ListPages<TitleEvaluationDO>
     * @return List<TitleEvaluationDO>
     */
    List<TitleEvaluationDO> getListTitleEvaluations(@Param("page") ListPages<TitleEvaluationDO> page);

    /**
     * 根据id查找记录总数
     *
     * @param id 职称评审信息id
     * @return rows
     */
    int getCountById(@Param("id") Integer id);

    /**
     * 删除职称评审信息
     *
     * @param id 职称评审信息id
     * @return 受影响rows
     */
    int deleteTitleEvaluationById(@Param("id") Integer id);

    /**
     * 获取职称评审信息详情
     *
     * @param id 职称评审信息id
     * @return TitleEvaluationDO
     */
    TitleEvaluationDO getDetailById(@Param("id") Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<TitleAssessorDO> page
     * @param id   职称评审id
     * @return ListPages<TitleAssessorDO>
     */
    List<TitleAssessorDO> getWaitAssessor(
            @Param("page") ListPages<TitleAssessorDO> page
            , @Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param id 职称评审id
     * @return Long
     */
    Long getCountWaitAssessorById(@Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param page            ListPages<TitleAssessorDO> page
     * @param id              职称评审id
     * @param listSelectedIds List<Integer> 已选职称评审人员id
     * @return Long
     */
    Long getCountUnselectedWaitAssessorById(
            @Param("page") ListPages<TitleAssessorDO> page
            , @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员数
     *
     * @param page            ListPages<TitleAssessorDO> page
     * @param id              职称评审id
     * @param listSelectedIds List<Integer> 已选职称评审人员id
     * @return Long
     */
    List<TitleAssessorDO> getUnselectedWaitAssessor(
            @Param("page") ListPages<TitleAssessorDO> page,
            @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员id
     *
     * @param id 职称评审id
     * @return Long
     */
    List<Integer> getUnselectedWaitAssessorIds(@Param("id") Integer id);

    /**
     * 查找已下单人员的姓名
     *
     * @param list 已下单人员id
     * @return List<String>
     */
    List<String> selectFullNameBatchByIds(@Param("id") Integer id, @Param("list") List<Integer> list);

    /**
     * 更新评审人员状态
     *
     * @param id              订单id
     * @param listSelectedIds 已选评审人员id
     * @return 受影响rows
     */
    int updateBatchAssessorStatusByIds(
            @Param("id") Integer id,
            @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取当日最大订单号
     *
     * @param today String
     * @return String
     */
    String getMaxOrderno(@Param("today") String today);

    /**
     * 获取代办总金额
     *
     * @param id              职称评审id
     * @param listSelectedIds 已选评审人员id
     * @return BigDecimal
     */
    BigDecimal getTotalAmount(
            @Param("id") Integer id,
            @Param("listSelectedIds") List<Integer> listSelectedIds
    );

    /**
     * 更新备注
     *
     * @param remarks RemarksRequest
     * @return 受影响rows
     */
    int updateRemarks(@Param("remarks") RemarksRequest remarks);

    /**
     * 更新职称评审记录状态
     *
     * @param id     职称评审id
     * @param status 状态
     * @return 受影响rows
     */
    int updateStatus(Integer id, int status);

    /**
     * 更新职称记录
     *
     * @param titleEvaluation TitleEvaluationDO
     * @return 受影响rows
     */
    int updateTitleEvaluationById(@Param("titleEvaluation") TitleEvaluationDO titleEvaluation);

    /**
     * 获取职称评审人员id集合
     *
     * @param id 职称评审id
     * @return List<Integer>
     */
    List<Integer> getListTitleEvaluationsIds(@Param("id") Integer id);

    /**
     * 条件获取职称评审列表总数
     *
     * @param search TitleEvaluationSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TitleEvaluationSearch search);

    /**
     * 条件分页获取职称评审列表
     *
     * @param page   ListPages<TitleEvaluationDO>
     * @param search TitleEvaluationSearch
     * @return List<TitleEvaluationDO>
     */
    List<TitleEvaluationDO> listTitleEvaluationsByConditionPages(
            @Param("page") ListPages<TitleEvaluationDO> page,
            @Param("search") TitleEvaluationSearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
