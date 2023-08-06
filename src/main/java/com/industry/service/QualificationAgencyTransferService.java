package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyApprovalFlowDO;
import com.industry.bean.entity.QualificationAgencyTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质代办转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationAgencyTransferService extends IService<QualificationAgencyTransferDO> {

    /**
     * 获取转账代办转账记录
     *
     * @param id 资质代办id
     * @return List<QualificationAgencyTransferDO>
     */
    List<QualificationAgencyTransferDO> getListTransferRecords(Integer id);


    /**
     * 插入资质转账记录
     *
     * @param transfer QualificationAgencyTransferDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAgencyTransferDO transfer);

    /**
     * 获取资质代办转账详情
     *
     * @param id 转账代办转账id
     * @return QualificationAgencyTransferDO
     */
    QualificationAgencyTransferDO getDetailById(Integer id);

    /**
     * 分页获取资质代办转账列表
     * @param page ListPages<QualificationAgencyTransferDO>
     * @return ListPages<QualificationAgencyTransferDO>
     */
    ListPages<QualificationAgencyTransferDO> getListTransferRecordsPage(ListPages<QualificationAgencyTransferDO> page);

    /**
     * 资质代办转账审核
     *
     * @param qualificationAgencyApprovalFlow QualificationAgencyApprovalFlowDO
     * @param currentAuditRoleId     当前审批角色id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity audit(QualificationAgencyApprovalFlowDO qualificationAgencyApprovalFlow, Integer currentAuditRoleId);
}
