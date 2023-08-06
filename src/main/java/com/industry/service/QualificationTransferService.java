package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.QualificationTransferSearch;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资质转让表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
public interface QualificationTransferService extends IService<QualificationTransferDO> {

    /**
     * 添加资质转让记录
     *
     * @param qualificationTransfer QualificationAcquisitionDO
     * @return 受影响rows
     */
    int insert(QualificationTransferDO qualificationTransfer);

    /**
     * 获取资质转让记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationTransferDO> listQualificationTransfer(Page<QualificationTransferDO> page);

    /**
     * 更新资质转让
     *
     * @param qualificationTransfer QualificationAcquisitionDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateQualificationTransferById(QualificationTransferDO qualificationTransfer);

    /**
     * 获取资质转让详情
     *
     * @param id 资质转让id
     * @return 受影响rows
     */
    QualificationTransferDO getDetailById(Integer id);

    /**
     * 分页获取所有可下单的资质收购信息
     *
     * @param page             ListPages<QualificationAcquisitionDO>
     * @param categoryAndGrade 资质类别和等级
     * @return ListPages<QualificationAcquisitionDO>
     */
    ListPages<QualificationAcquisitionDO> getListCanPlaceOrders(ListPages<QualificationAcquisitionDO> page, String categoryAndGrade);

    /**
     * 分页获取未选转让客户
     *
     * @param page                     ListPages<QualificationAcquisitionDO>
     * @param categoryAndGrade         资质类别和等级
     * @param selectedTransferCustomer 已选转让客户id
     * @return ListPages<QualificationAcquisitionDO>
     */
    ListPages<QualificationAcquisitionDO> getListUnselectTransferCustomers(ListPages<QualificationAcquisitionDO> page, String categoryAndGrade, Integer selectedTransferCustomer);

    /**
     * 条件分页获取资质转让列表
     *
     * @param page   ListPages<QualificationTransferDO>
     * @param search QualificationTransferSearch
     * @return ListPages<QualificationTransferDO>
     */
    ListPages<QualificationTransferDO> listQualificationTransfersByConditionPages(ListPages<QualificationTransferDO> page, QualificationTransferSearch search);

    /**
     * 删除资质转让
     *
     * @param id 资质转让id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 恢复数据
     *
     * @param id 资质转让id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int recoveryById(Integer id);
}
