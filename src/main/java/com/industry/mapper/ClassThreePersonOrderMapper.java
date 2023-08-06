package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.ClassThreeAssessorDO;
import com.industry.bean.entity.ClassThreePersonOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 三类人员订单表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Mapper
public interface ClassThreePersonOrderMapper extends BaseMapper<ClassThreePersonOrderDO> {

    int insertOrder(ClassThreePersonOrderDO order);

    /**
     * 获取所有三类人员订单
     *
     * @param id 三类人员id
     * @return List<ClassThreePersonOrderDO>
     */
    List<ClassThreePersonOrderDO> listOrders(@Param("id") Integer id);


    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return ClassThreePersonOrderDO
     */
    ClassThreePersonOrderDO getDetailById(@Param("id") Integer id);

    /**
     * 获取所有三类人员订单
     *
     * @param page         ListPages<ClassThreePersonOrderDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return List<ClassThreePersonOrderDO>
     */
    List<ClassThreePersonOrderDO> listAllOrders(@Param("page") ListPages<ClassThreePersonOrderDO> page,
                                                @Param("customerName") String customerName,
                                                @Param("status") Integer status);

    /**
     * 获取三类人员订单总数
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
     * 查询三类人员订单
     *
     * @param id 三类人员订单id
     * @return ClassThreePersonOrderDO
     */
    ClassThreePersonOrderDO selectOrderById(@Param("id") Integer id);

    /**
     * 更新三类人员订单状态
     *
     * @param id     三类人员订单id
     * @param status 状态
     * @return 受影响rows
     */
    int updateOrderById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 获取三类人员人员列表
     *
     * @param id 订单id
     * @return List<ClassThreeAssessorDO>
     */
    List<ClassThreeAssessorDO> listAssessorsByOrderId(@Param("id") Integer id);
}
