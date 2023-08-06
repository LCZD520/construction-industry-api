package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.UpdateAssessorRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 职称评审订单表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Mapper
public interface TitleEvaluationOrderMapper extends BaseMapper<TitleEvaluationOrderDO> {

    int insertOrder(TitleEvaluationOrderDO order);

    /**
     * 获取所有职称评审订单
     *
     * @param id 职称评审id
     * @return List<TitleEvaluationOrderDO>
     */
    List<TitleEvaluationOrderDO> listOrders(@Param("id") Integer id);


    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return TitleEvaluationOrderDO
     */
    TitleEvaluationOrderDO getDetailById(@Param("id") Integer id);

    /**
     * 获取所有职称评审订单
     *
     * @param page         ListPages<TitleEvaluationOrderDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return List<TitleEvaluationOrderDO>
     */
    List<TitleEvaluationOrderDO> listAllOrders(@Param("page") ListPages<TitleEvaluationOrderDO> page,
                                               @Param("customerName") String customerName,
                                               @Param("status") Integer status);

    /**
     * 获取职称评审订单总数
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
     * 查询职称评审订单
     *
     * @param id 职称评审订单id
     * @return TitleEvaluationOrderDO
     */
    TitleEvaluationOrderDO selectOrderById(@Param("id") Integer id);

    /**
     * 更新职称评审订单状态
     *
     * @param id     职称评审订单id
     * @param status 状态
     * @return 受影响rows
     */
    int updateOrderById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 获取职称评审人员列表
     *
     * @param id 订单id
     * @return List<TitleAssessorDO>
     */
    List<TitleAssessorDO> listAssessorsByOrderId(@Param("id") Integer id);
}
