package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学历提升订单表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Mapper
public interface EducationPromotionOrderMapper extends BaseMapper<EducationPromotionOrderDO> {

    int insertOrder(EducationPromotionOrderDO order);

    /**
     * 获取所有学历提升订单
     *
     * @param id 学历提升id
     * @return List<EducationPromotionOrderDO>
     */
    List<EducationPromotionOrderDO> listOrders(@Param("id") Integer id);


    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return EducationPromotionOrderDO
     */
    EducationPromotionOrderDO getDetailById(@Param("id") Integer id);

    /**
     * 获取所有学历提升订单
     *
     * @param page         ListPages<EducationPromotionOrderDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return List<EducationPromotionOrderDO>
     */
    List<EducationPromotionOrderDO> listAllOrders(@Param("page") ListPages<EducationPromotionOrderDO> page,
                                                  @Param("customerName") String customerName,
                                                  @Param("status") Integer status);

    /**
     * 获取学历提升订单总数
     *
     * @param customerName 客户名称
     * @param status       订单状态
     * @return Long
     */
    Long getCountListAllOrders(@Param("customerName") String customerName,
                               @Param("status") Integer status);

    /**
     * 查询评审人员id集合
     *
     * @param ids 评审人员id
     * @return List<Integer>
     */
    List<Integer> selectIdsByIds(@Param("ids") List<Integer> ids);

    /**
     * 更新评审人员代办费用、评审费用
     *
     * @param list List<UpdateAssessorRequest>
     * @return 受影响rows
     */
    int updateAssessorByIds(@Param("list") List<UpdateAssessorRequest> list);

    /**
     * 查询学历提升订单
     *
     * @param id 学历提升订单id
     * @return EducationPromotionOrderDO
     */
    EducationPromotionOrderDO selectOrderById(@Param("id") Integer id);

    /**
     * 更新学历提升订单状态
     *
     * @param id     学历提升订单id
     * @param status 状态
     * @return 受影响rows
     */
    int updateOrderById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 获取学历提升人员列表
     *
     * @param id 订单id
     * @return List<EducationPromotionAssessorDO>
     */
    List<EducationPromotionAssessorDO> listAssessorsByOrderId(@Param("id") Integer id);
}
