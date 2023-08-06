package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferOrderDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.QualificationTransferOrderSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质转让订单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-11-27
 */
public interface QualificationTransferOrderService extends IService<QualificationTransferOrderDO> {

    /**
     * 资质转让下单
     *
     * @param qualificationTransfer QualificationTransferOrderDO
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity placeOrder(QualificationTransferOrderDO qualificationTransfer);

    /**
     * 获取订单列表
     *
     * @param id 资质转让id
     * @return List<QualificationTransferOrderDO>
     */
    List<QualificationTransferOrderDO> getListOrders(Integer id);

    /**
     * 根据订单id获取订单详情
     *
     * @param id 资质转让id
     * @return QualificationTransferOrderDO
     */
    QualificationTransferOrderDO getOrderDetailById(Integer id);

    /**
     * 分页获取资质转让订单
     *
     * @param page ListPages<QualificationTransferOrderDO>
     * @return ListPages<QualificationTransferOrderDO>
     */
    ListPages<QualificationTransferOrderDO> listQualificationTransferOrders(ListPages<QualificationTransferOrderDO> page);

    /**
     * 条件获取资质转账订单列表
     *
     * @param page   ListPages<QualificationTransferOrderDO>
     * @param search QualificationTransferOrderSearch
     * @return ListPages<QualificationTransferOrderDO>
     */
    ListPages<QualificationTransferOrderDO> listByConditionPages(ListPages<QualificationTransferOrderDO> page, QualificationTransferOrderSearch search);
}
