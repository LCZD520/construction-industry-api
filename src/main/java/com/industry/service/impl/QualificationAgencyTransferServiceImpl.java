package com.industry.service.impl;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.entity.QualificationAgencyTransferDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.mapper.QualificationAgencyApprovalFlowMapper;
import com.industry.mapper.QualificationAgencyApprovalFlowMapper;
import com.industry.mapper.QualificationAgencyTransferMapper;
import com.industry.service.QualificationAgencyTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class QualificationAgencyTransferServiceImpl extends ServiceImpl<QualificationAgencyTransferMapper, QualificationAgencyTransferDO> implements QualificationAgencyTransferService {

    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private QualificationAgencyTransferMapper mapper;

    private QualificationAgencyApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(QualificationAgencyTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Autowired
    public void setApprovalFlowMapper(QualificationAgencyApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Override
    public List<QualificationAgencyTransferDO> getListTransferRecords(Integer id) {
        return mapper.getListTransferRecords(id);
    }

    @Override
    public int insert(QualificationAgencyTransferDO transfer) {
        return mapper.insert(transfer);
    }

    @Override
    public QualificationAgencyTransferDO getDetailById(Integer id) {
        QualificationAgencyTransferDO transfer = mapper.getDetailById(id);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                return transfer;
            }
            final QualificationAgencyApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationAgencyTransferId(transfer.getId());
            if (latestRecord != null) {
                transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
            }
        }
        return transfer;
    }

    @Override
    public ListPages<QualificationAgencyTransferDO> getListTransferRecordsPage(ListPages<QualificationAgencyTransferDO> page) {
        List<QualificationAgencyTransferDO> list
                = mapper.getListTransferRecordsPage(page);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final QualificationAgencyTransferDO transfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                    transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final QualificationAgencyApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByQualificationAgencyTransferId(transfer.getId());
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
    public ResultEntity audit(QualificationAgencyApprovalFlowDO qualificationAgencyApprovalFlow, Integer currentAuditRoleId) {
        final Integer qualificationAgencyTransferId = qualificationAgencyApprovalFlow.getQualificationAgencyTransferId();
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
            final QualificationAgencyApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationAgencyTransferId(qualificationAgencyTransferId);
            final Boolean auditResult = qualificationAgencyApprovalFlow.getAuditResult();
            String auditStatus = roleName;
            if (auditResult) {
                Integer roleId = null;
                for (int i = 0, len = settings.size(); i < len; i++) {
                    int nextIndex = i + 1;
                    if (settings.get(i).getRoleId().equals(currentAuditRoleId) && nextIndex < len) {
                        roleId = settings.get(nextIndex).getRoleId();
                    }
                }
                qualificationAgencyApprovalFlow.setNextAuditRoleId(roleId);
                auditStatus = auditStatus + "审批通过";
            } else {
                auditStatus = auditStatus + "审批不通过";
            }
            qualificationAgencyApprovalFlow.setAuditStatus(auditStatus);
            mapper.updateApplicationStatusById(qualificationAgencyTransferId, auditStatus);
            int rows = 0;
            if (latestRecord != null) {
                if (latestRecord.getNextAuditRoleId() == null) {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
                if (latestRecord.getNextAuditRoleId().equals(currentAuditRoleId)) {
                    rows = approvalFlowMapper.insertAuditRecord(qualificationAgencyApprovalFlow);
                } else {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
            } else {
                rows = approvalFlowMapper.insertAuditRecord(qualificationAgencyApprovalFlow);
            }
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_AUDIT_RECORD);
        }
    }

}
