package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.QualificationTransferOrderSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质转让订单表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-11-27
 */
@Mapper
public interface QualificationTransferOrderMapper extends BaseMapper<QualificationTransferOrderDO> {

    /**
     * 根据资质转让id和资质收购id判断是否存在重复订单
     *
     * @param transferCustomerId      转让意向客户id（资质收购id）
     * @param qualificationTransferId 资质转让id
     * @return QualificationTransferOrderDO
     */
    QualificationTransferOrderDO getOrderByTransferCustomerIdAndQualificationTransferId(
            @Param("transferCustomerId") Integer transferCustomerId,
            @Param("qualificationTransferId") Integer qualificationTransferId);

    /**
     * 根据资质收购id查询订单
     *
     * @param transferCustomerId 转让意向客户id（资质收购id）
     * @return QualificationTransferOrderDO
     */
    QualificationTransferOrderDO getOrderByTransferCustomerId(
            @Param("transferCustomerId") Integer transferCustomerId);

    /**
     * 添加资质转让订单
     *
     * @param order QualificationTransferOrderDO
     * @return 受影响rows
     */
    int insertOrder(QualificationTransferOrderDO order);


    /**
     * 获取资质转让订单列表
     *
     * @param id 资质转让id
     * @return List<QualificationTransferOrderDO>
     */
    List<QualificationTransferOrderDO> getListOrders(@Param("id") Integer id);

    /**
     * 获取当日最大订单号
     *
     * @param today String
     * @return String
     */
    String getMaxOrderno(@Param("today") String today);

    /**
     * 获取订单详情
     *
     * @param id 资质转让订单id
     * @return QualificationTransferOrderDO
     */
    QualificationTransferOrderDO getOrderDetailById(@Param("id") Integer id);

    /**
     * 获取资质转让订单总数
     *
     * @return Long
     */
    Long getCountListAllOrders();

    /**
     * 分页获取资质转让订单
     *
     * @param page ListPages<QualificationTransferOrderDO>
     * @return List<QualificationTransferOrderDO>
     */
    List<QualificationTransferOrderDO> listQualificationTransferOrders(@Param("page") ListPages<QualificationTransferOrderDO> page);

    /**
     * 条件获取资质转让订单
     *
     * @param page   ListPages<QualificationTransferOrderDO>
     * @param search QualificationTransferOrderSearch
     * @return List<QualificationTransferOrderDO>
     */
    List<QualificationTransferOrderDO> listByConditionPages(
            @Param("page") ListPages<QualificationTransferOrderDO> page,
            @Param("search") QualificationTransferOrderSearch search);

    /**
     * 条件获取资质转让订单总数
     *
     * @param search QualificationTransferOrderSearch
     * @return List<QualificationTransferOrderDO>
     */
    Long getCountByCondition(@Param("search") QualificationTransferOrderSearch search);
}
