package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ApprovalSettingDO;
import com.industry.bean.entity.QualificationAcquisitionApprovalFlowDO;
import com.industry.bean.entity.QualificationAcquisitionTransferDO;
import com.industry.bean.entity.RoleDO;
import com.industry.bean.search.QualificationAcquisitionTransferSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.mapper.QualificationAcquisitionApprovalFlowMapper;
import com.industry.mapper.QualificationAcquisitionTransferMapper;
import com.industry.service.QualificationAcquisitionTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资质代办转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Service
public class QualificationAcquisitionTransferServiceImpl extends ServiceImpl<QualificationAcquisitionTransferMapper, QualificationAcquisitionTransferDO> implements QualificationAcquisitionTransferService {

    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private QualificationAcquisitionTransferMapper mapper;

    private QualificationAcquisitionApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(QualificationAcquisitionTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Autowired
    public void setApprovalFlowMapper(QualificationAcquisitionApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Override
    public List<QualificationAcquisitionTransferDO> getListTransferRecords(Integer id) {
        return mapper.getListTransferRecords(id);
    }

    @Override
    public int insert(QualificationAcquisitionTransferDO transfer) {
        return mapper.insert(transfer);
    }

    @Override
    public QualificationAcquisitionTransferDO getDetailById(Integer id) {
        QualificationAcquisitionTransferDO transfer = mapper.getDetailById(id);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                return transfer;
            }
            final QualificationAcquisitionApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationAcquisitionTransferId(transfer.getId());
            if (latestRecord != null) {
                transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
            }
        }
        return transfer;
    }

    @Override
    public ListPages<QualificationAcquisitionTransferDO> getListTransferRecordsPage(ListPages<QualificationAcquisitionTransferDO> page) {
        List<QualificationAcquisitionTransferDO> list
                = mapper.getListTransferRecordsPage(page);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final QualificationAcquisitionTransferDO transfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                    transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final QualificationAcquisitionApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByQualificationAcquisitionTransferId(transfer.getId());
                if (latestRecord != null) {
                    transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
                }
            }
        }
        page.setList(list);
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public ResultEntity audit(QualificationAcquisitionApprovalFlowDO qualificationAcquisitionApprovalFlow, Integer currentAuditRoleId) {
        final Integer qualificationAcquisitionTransferId = qualificationAcquisitionApprovalFlow.getQualificationAcquisitionTransferId();
        synchronized (this) {
            final List<ApprovalSettingDO> settings
                    = settingMapper.listApprovalSettingsByType(2);
            if (settings.isEmpty()) {
                return result.failure(ResultCodeEnum.FAIL_UNSET_TRANSFER_AUDIT);
            }
            final AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final List<Integer> listRoleIds = user.getListRoleIds();
            final List<RoleDO> listRoles = user.getListRoles();
            if (listRoleIds.isEmpty()) {
                return result.failure(ResultCodeEnum.FAIL_NOT_FOUND_ANY_ROLE);
            }
            String roleName = "";
            for (RoleDO role : listRoles) {
                if (role.getRoleId().equals(currentAuditRoleId)) {
                    roleName = role.getRoleName();
                }
            }
            final QualificationAcquisitionApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationAcquisitionTransferId(qualificationAcquisitionTransferId);
            final Boolean auditResult = qualificationAcquisitionApprovalFlow.getAuditResult();
            String auditStatus = roleName;
            if (auditResult) {
                Integer roleId = null;
                for (int i = 0, len = settings.size(); i < len; i++) {
                    int nextIndex = i + 1;
                    if (settings.get(i).getRoleId().equals(currentAuditRoleId) && nextIndex < len) {
                        roleId = settings.get(nextIndex).getRoleId();
                    }
                }
                qualificationAcquisitionApprovalFlow.setNextAuditRoleId(roleId);
                auditStatus = auditStatus + "审批通过";
            } else {
                auditStatus = auditStatus + "审批不通过";
            }
            qualificationAcquisitionApprovalFlow.setAuditStatus(auditStatus);
            mapper.updateApplicationStatusById(qualificationAcquisitionTransferId, auditStatus);
            int rows = 0;
            if (latestRecord != null) {
                if (latestRecord.getNextAuditRoleId() == null) {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
                if (latestRecord.getNextAuditRoleId().equals(currentAuditRoleId)) {
                    rows = approvalFlowMapper.insertAuditRecord(qualificationAcquisitionApprovalFlow);
                } else {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
            } else {
                rows = approvalFlowMapper.insertAuditRecord(qualificationAcquisitionApprovalFlow);
            }
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_AUDIT_RECORD);
        }
    }

    @Override
    public ListPages<QualificationAcquisitionTransferDO> listTransferRecords(ListPages<QualificationAcquisitionTransferDO> page, QualificationAcquisitionTransferSearch search) {
        List<QualificationAcquisitionTransferDO> list
                = mapper.listTransferRecords(page, search);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final QualificationAcquisitionTransferDO transfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                    transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final QualificationAcquisitionApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByQualificationAcquisitionTransferId(transfer.getId());
                if (latestRecord != null) {
                    transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
                }
            }
        }
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

}
