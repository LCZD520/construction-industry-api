package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.EducationPromotionOrderMapper;
import com.industry.service.EducationPromotionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学历提升订单表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Service
public class EducationPromotionOrderServiceImpl extends ServiceImpl<EducationPromotionOrderMapper, EducationPromotionOrderDO> implements EducationPromotionOrderService {

    /**
     * 订单执行中
     */
    private static final Integer EXECUTING_ORDER_STATUS = 2;

    /**
     * 订单已取消
     */
    private static final Integer CANCELED_ORDER_STATUS = 4;

    private ResultEntity result;

    private EducationPromotionOrderMapper mapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(EducationPromotionOrderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<EducationPromotionOrderDO> listOrders(Integer id) {
        return mapper.listOrders(id);
    }

    @Override
    public EducationPromotionOrderDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public ListPages<EducationPromotionOrderDO> listAllOrders(ListPages<EducationPromotionOrderDO> page,
                                                           String customerName,
                                                           Integer status) {
        List<EducationPromotionOrderDO> listEducationPromotions = mapper.listAllOrders(page, customerName, status);
        page.setList(listEducationPromotions);
        page.setTotal(mapper.getCountListAllOrders(customerName, status));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public int updateAssessorByIds(List<UpdateAssessorRequest> list, Integer id) {
        synchronized (this) {
            final List<Integer> ids = new ArrayList<>();
            list.forEach(item -> ids.add(item.getId()));
            final List<Integer> integers = mapper.selectIdsByIds(ids);
            if (integers.size() == ids.size()) {
                final EducationPromotionOrderDO titleEvaluationOrder = mapper.selectOrderById(id);
                if (titleEvaluationOrder != null) {
                    mapper.updateOrderById(id, EXECUTING_ORDER_STATUS);
                }
                return mapper.updateAssessorByIds(list);
            }
            return -1;
        }
    }

    @Override
    public List<EducationPromotionAssessorDO> listAssessorsByOrderId(Integer id) {
        return mapper.listAssessorsByOrderId(id);
    }

    @Override
    public ResultEntity orderCancel(Integer id) {
        synchronized (this) {
            final EducationPromotionOrderDO order = mapper.selectById(id);
            if (null == order) {
                return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
            }
            if (!EXECUTING_ORDER_STATUS.equals(order.getOrderStatus())) {
                return result.success(ResultCodeEnum.FAIL_ORDER_CANCELED_EXIST_LATEST_STATUS);
            }
            final int rows = mapper.updateOrderById(id, CANCELED_ORDER_STATUS);
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_ORDER_CANCELED);
            }
            return result.failure(ResultCodeEnum.FAIL_ORDER_CANCELED);
        }
    }
}
