package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.EducationPromotionSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 学历提升表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface EducationPromotionMapper extends BaseMapper<EducationPromotionDO> {
    /**
     * 插入学历提升信息
     *
     * @param educationPromotion EducationPromotionDO
     * @return 受影响rows
     */
    int insertEducationPromotion(EducationPromotionDO educationPromotion);

    /**
     * 学历提升信息总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取学历提升信息
     *
     * @param page ListPages<EducationPromotionDO>
     * @return List<EducationPromotionDO>
     */
    List<EducationPromotionDO> getListEducationPromotions(@Param("page") ListPages<EducationPromotionDO> page);

    /**
     * 根据id查找记录总数
     *
     * @param id 学历提升信息id
     * @return rows
     */
    int getCountById(@Param("id") Integer id);

    /**
     * 删除学历提升信息
     *
     * @param id 学历提升信息id
     * @return 受影响rows
     */
    int deleteEducationPromotionById(@Param("id") Integer id);

    /**
     * 获取学历提升信息详情
     *
     * @param id 学历提升信息id
     * @return EducationPromotionDO
     */
    EducationPromotionDO getDetailById(@Param("id") Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<EducationPromotionAssessorDO> page
     * @param id   学历提升id
     * @return ListPages<EducationPromotionAssessorDO>
     */
    List<EducationPromotionAssessorDO> getWaitAssessor(
            @Param("page") ListPages<EducationPromotionAssessorDO> page
            , @Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param id 学历提升id
     * @return Long
     */
    Long getCountWaitAssessorById(@Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param page            ListPages<EducationPromotionAssessorDO> page
     * @param id              学历提升id
     * @param listSelectedIds List<Integer> 已选学历提升人员id
     * @return Long
     */
    Long getCountUnselectedWaitAssessorById(
            @Param("page") ListPages<EducationPromotionAssessorDO> page
            , @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员数
     *
     * @param page            ListPages<EducationPromotionAssessorDO> page
     * @param id              学历提升id
     * @param listSelectedIds List<Integer> 已选学历提升人员id
     * @return Long
     */
    List<EducationPromotionAssessorDO> getUnselectedWaitAssessor(
            @Param("page") ListPages<EducationPromotionAssessorDO> page,
            @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员id
     *
     * @param id 学历提升id
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
     * @param id              学历提升id
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
     * 更新学历提升记录状态
     *
     * @param id     学历提升id
     * @param status 状态
     * @return 受影响rows
     */
    int updateStatus(Integer id, int status);

    /**
     * 更新职称记录
     *
     * @param educationPromotion EducationPromotionDO
     * @return 受影响rows
     */
    int updateEducationPromotionById(@Param("educationPromotion") EducationPromotionDO educationPromotion);

    /**
     * 获取学历提升人员id集合
     *
     * @param id 学历提升id
     * @return List<Integer>
     */
    List<Integer> getListEducationPromotionsIds(@Param("id") Integer id);

    /**
     * 条件获取学历提升列表总数
     *
     * @param search EducationPromotionSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") EducationPromotionSearch search);

    /**
     * 条件分页获取学历提升列表
     *
     * @param page   ListPages<EducationPromotionDO>
     * @param search EducationPromotionSearch
     * @return List<EducationPromotionDO>
     */
    List<EducationPromotionDO> listEducationPromotionsByConditionPages(
            @Param("page") ListPages<EducationPromotionDO> page,
            @Param("search") EducationPromotionSearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
