package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationOrderDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.UpdateAssessorRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 职称评审订单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
public interface TitleEvaluationOrderService extends IService<TitleEvaluationOrderDO> {

    /**
     * 获取所有职称评审订单
     *
     * @param id 职称评审id
     * @return List<TitleEvaluationOrderDO>
     */
    List<TitleEvaluationOrderDO> listOrders(Integer id);

    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return TitleEvaluationOrderDO
     */
    TitleEvaluationOrderDO getDetailById(Integer id);

    /**
     * 获取所有职称评审订单
     *
     * @param page         ListPages<TitleEvaluationDO>
     * @param customerName 客户名称
     * @param status       订单状态
     * @return ListPages<TitleEvaluationDO>
     */
    ListPages<TitleEvaluationOrderDO> listAllOrders(ListPages<TitleEvaluationOrderDO> page,
                                                    String customerName,
                                                    Integer status);

    /**
     * 编辑职称评审人员
     *
     * @param list List<UpdateAssessorRequest>
     * @param id   职称评审订单id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateAssessorByIds(List<UpdateAssessorRequest> list, Integer id);

    /**
     * 获取职称评审人员列表
     *
     * @param id 订单id
     * @return List<TitleAssessorDO>
     */
    List<TitleAssessorDO> listAssessorsByOrderId(Integer id);

    /**
     * 职称评审订单取消
     *
     * @param id   职称评审订单id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity orderCancel(Integer id);
}
