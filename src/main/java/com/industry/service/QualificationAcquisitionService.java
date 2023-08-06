package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.QualificationAcquisitionStrippingRequest;
import com.industry.bean.search.QualificationAcquisitionSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质收购表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
public interface QualificationAcquisitionService extends IService<QualificationAcquisitionDO> {

    /**
     * 添加资质收购记录
     *
     * @param qualificationAcquisition QualificationAcquisitionDO
     * @return 受影响rows
     */
    int insert(QualificationAcquisitionDO qualificationAcquisition);

    /**
     * 获取资质收购记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page);

    /**
     * 更新资质收购
     *
     * @param qualificationAcquisition QualificationAcquisitionDO
     * @return 受影响rows
     */
    int updateQualificationAcquisitionById(QualificationAcquisitionDO qualificationAcquisition);

    /**
     * 获取资质收购详情
     *
     * @param id 资质收购id
     * @return 受影响rows
     */
    QualificationAcquisitionDO getDetailById(Integer id);

    /**
     * 获取资质类别及登记
     *
     * @param id 资质收购id
     * @return String
     */
    List<String> getQualificationById(Integer id);

    /**
     * 资质剥离
     *
     * @param id      资质剥离id
     * @param request QualificationAcquisitionStrippingRequest
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int stripping(Integer id, QualificationAcquisitionStrippingRequest request);

    /**
     * 根据转让意向用户查找资质收购记录
     *
     * @param transferCustomer 转让意向用户
     * @return QualificationAcquisitionDO
     */
    QualificationAcquisitionDO getQualificationAcquisitionByTransferCustomer(String transferCustomer);

    /**
     * 删除资质收购
     *
     * @param id 资质收购id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 恢复数据
     *
     * @param id 资质收购id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int recoveryById(Integer id);

    /**
     * 条件分页获取资质收购列表
     * @param page ListPages<QualificationAcquisitionDO>
     * @param search QualificationAcquisitionSearch
     * @return ListPages<QualificationAcquisitionDO>
     */
    ListPages<QualificationAcquisitionDO> listQualificationAcquisitionsByConditionPages(ListPages<QualificationAcquisitionDO> page, QualificationAcquisitionSearch search);
}
