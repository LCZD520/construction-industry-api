package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.search.QualificationTransferSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质转让表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Mapper
public interface QualificationTransferMapper extends BaseMapper<QualificationTransferDO> {

    /**
     * 获取资质转让记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationTransferDO> listQualificationTransfer(@Param("page") Page<QualificationTransferDO> page);

    /**
     * 分页获取所有可下单的资质收购信息
     *
     * @param page             ListPages<QualificationAcquisitionDO>
     * @param categoryAndGrade 资质类别和等级
     * @return List<QualificationAcquisitionDO>
     */
    List<QualificationAcquisitionDO> getListCanPlaceOrders(@Param("page") ListPages<QualificationAcquisitionDO> page, @Param("categoryAndGrade") String categoryAndGrade);

    /**
     * 获取所有可下单的意向转让客户
     *
     * @param categoryAndGrade 资质类别和等级
     * @return Long
     */
    Long countCanPlaceOrders(@Param("categoryAndGrade") String categoryAndGrade);

    /**
     * 获取所有可下单的意向转让客户
     *
     * @param categoryAndGrade         资质类别和等级
     * @param selectedTransferCustomer 已选转让客户id
     * @return Long
     */
    Long countUnselectTransferCustomers(@Param("categoryAndGrade") String categoryAndGrade,
                                        @Param("selectedTransferCustomer") Integer selectedTransferCustomer);

    /**
     * 获取未选转让客户
     *
     * @param page                     ListPages<QualificationAcquisitionDO>
     * @param categoryAndGrade         资质类别和等级
     * @param selectedTransferCustomer 已选转让客户id
     * @return List<QualificationAcquisitionDO>
     */
    List<QualificationAcquisitionDO> getListUnselectTransferCustomers(
            @Param("page") ListPages<QualificationAcquisitionDO> page,
            @Param("categoryAndGrade") String categoryAndGrade,
            @Param("selectedTransferCustomer") Integer selectedTransferCustomer);

    /**
     * 根据id获取资质转让详情
     *
     * @param id 资质转让id
     * @return QualificationTransferDO
     */
    QualificationTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 更新资质转让记录状态
     *
     * @param id     资质转让id
     * @param status 状态
     * @return 受影响rows
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 条件分页获取资质转让列表
     *
     * @param page   ListPages<QualificationTransferDO>
     * @param search QualificationTransferSearch
     * @return ListPages<QualificationTransferDO>
     */
    List<QualificationTransferDO> listQualificationTransfersByConditionPages(
            @Param("page") ListPages<QualificationTransferDO> page,
            @Param("search") QualificationTransferSearch search);

    /**
     * 条件获取资质转让列表总数
     *
     * @param search QualificationTransferSearch
     * @return Long
     */
    Long getCount(@Param("search") QualificationTransferSearch search);


    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
