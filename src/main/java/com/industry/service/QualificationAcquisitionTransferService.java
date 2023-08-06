package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAcquisitionApprovalFlowDO;
import com.industry.bean.entity.QualificationAcquisitionTransferDO;
import com.industry.bean.search.QualificationAcquisitionTransferSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质收购转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationAcquisitionTransferService extends IService<QualificationAcquisitionTransferDO> {

    /**
     * 获取资质收购转账记录
     *
     * @param id 资质收购id
     * @return List<QualificationAcquisitionTransferDO>
     */
    List<QualificationAcquisitionTransferDO> getListTransferRecords(Integer id);

    /**
     * 插入资质转账记录
     *
     * @param transfer QualificationAcquisitionTransferDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAcquisitionTransferDO transfer);

    /**
     * 获取资质收购转账详情
     *
     * @param id 转账收购转账id
     * @return QualificationAcquisitionTransferDO
     */
    QualificationAcquisitionTransferDO getDetailById(Integer id);

    /**
     * 分页获取资质收购转账列表
     *
     * @param page ListPages<QualificationAcquisitionTransferDO>
     * @return ListPages<QualificationAcquisitionTransferDO>
     */
    ListPages<QualificationAcquisitionTransferDO> getListTransferRecordsPage(ListPages<QualificationAcquisitionTransferDO> page);

    /**
     * 资质收购转账审核
     *
     * @param qualificationAcquisitionApprovalFlow QualificationAcquisitionApprovalFlowDO
     * @param currentAuditRoleId                   当前审批角色id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity audit(QualificationAcquisitionApprovalFlowDO qualificationAcquisitionApprovalFlow, Integer currentAuditRoleId);

    /**
     * 条件分页获取转账记录
     *
     * @param page   ListPages<QualificationAcquisitionTransferDO>
     * @param search QualificationAcquisitionTransferSearch
     * @return ListPages<QualificationAcquisitionTransferDO>
     */
    ListPages<QualificationAcquisitionTransferDO> listTransferRecords(ListPages<QualificationAcquisitionTransferDO> page, QualificationAcquisitionTransferSearch search);
}
