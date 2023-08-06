package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 学历提升订单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
public interface EducationPromotionOrderService extends IService<EducationPromotionOrderDO> {

    /**
     * 获取所有学历提升订单
     *
     * @param id 学历提升id
     * @return List<EducationPromotionOrderDO>
     */
    List<EducationPromotionOrderDO> listOrders(Integer id);

    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return EducationPromotionOrderDO
     */
    EducationPromotionOrderDO getDetailById(Integer id);

    /**
     * 获取所有学历提升订单
     *
     * @param page         ListPages<EducationPromotionDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return ListPages<EducationPromotionDO>
     */
    ListPages<EducationPromotionOrderDO> listAllOrders(ListPages<EducationPromotionOrderDO> page,
                                                       String customerName,
                                                       Integer status);

    /**
     * 编辑学历提升人员
     *
     * @param list List<UpdateAssessorRequest>
     * @param id   学历提升订单id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateAssessorByIds(List<UpdateAssessorRequest> list, Integer id);

    /**
     * 获取学历提升人员列表
     *
     * @param id 订单id
     * @return List<EducationPromotionAssessorDO>
     */
    List<EducationPromotionAssessorDO> listAssessorsByOrderId(Integer id);

    /**
     * 学历提升订单取消
     *
     * @param id 学历提升订单id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity orderCancel(Integer id);
}
