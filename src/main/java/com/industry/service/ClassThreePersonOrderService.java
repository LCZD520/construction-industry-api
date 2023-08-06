package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ClassThreeAssessorDO;
import com.industry.bean.entity.ClassThreePersonOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 三类人员订单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
public interface ClassThreePersonOrderService extends IService<ClassThreePersonOrderDO> {

    /**
     * 获取所有三类人员订单
     *
     * @param id 三类人员id
     * @return List<ClassThreePersonOrderDO>
     */
    List<ClassThreePersonOrderDO> listOrders(Integer id);

    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return ClassThreePersonOrderDO
     */
    ClassThreePersonOrderDO getDetailById(Integer id);

    /**
     * 获取所有三类人员订单
     *
     * @param page         ListPages<ClassThreePersonDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return ListPages<ClassThreePersonDO>
     */
    ListPages<ClassThreePersonOrderDO> listAllOrders(ListPages<ClassThreePersonOrderDO> page,
                                                     String customerName,
                                                     Integer status);

    /**
     * 编辑三类人员人员
     *
     * @param list List<UpdateAssessorRequest>
     * @param id   三类人员订单id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateAssessorByIds(List<UpdateAssessorRequest> list, Integer id);

    /**
     * 获取三类人员人员列表
     *
     * @param id 订单id
     * @return List<ClassThreeAssessorDO>
     */
    List<ClassThreeAssessorDO> listAssessorsByOrderId(Integer id);

    /**
     * 三类人员订单取消
     *
     * @param id 三类人员订单id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity orderCancel(Integer id);
}
