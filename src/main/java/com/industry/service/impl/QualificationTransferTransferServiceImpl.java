package com.industry.service.impl;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.search.QualificationTransferTransferSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.mapper.QualificationTransferApprovalFlowMapper;
import com.industry.mapper.QualificationTransferTransferMapper;
import com.industry.mapper.QualificationTransferTransferMapper;
import com.industry.service.QualificationTransferTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资质转让转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Service
public class QualificationTransferTransferServiceImpl extends ServiceImpl<QualificationTransferTransferMapper, QualificationTransferTransferDO> implements QualificationTransferTransferService {

    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private QualificationTransferTransferMapper mapper;

    private QualificationTransferApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(QualificationTransferTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Autowired
    public void setApprovalFlowMapper(QualificationTransferApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Override
    public List<QualificationTransferTransferDO> getListTransferRecords(Integer id) {
        return mapper.getListTransferRecords(id);
    }

    @Override
    public int insert(QualificationTransferTransferDO transfer) {
        return mapper.insert(transfer);
    }

    @Override
    public QualificationTransferTransferDO getDetailById(Integer id) {
        QualificationTransferTransferDO transfer = mapper.getDetailById(id);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                return transfer;
            }
            final QualificationTransferApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationTransferTransferId(transfer.getId());
            if (latestRecord != null) {
                transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
            }
        }
        return transfer;
    }

    @Override
    public ResultEntity audit(QualificationTransferApprovalFlowDO qualificationTransferApprovalFlow, Integer currentAuditRoleId) {
        final Integer qualificationTransferTransferId = qualificationTransferApprovalFlow.getQualificationTransferTransferId();
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
            final QualificationTransferApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByQualificationTransferTransferId(qualificationTransferTransferId);
            final Boolean auditResult = qualificationTransferApprovalFlow.getAuditResult();
            String auditStatus = roleName;
            if (auditResult) {
                Integer roleId = null;
                for (int i = 0, len = settings.size(); i < len; i++) {
                    int nextIndex = i + 1;
                    if (settings.get(i).getRoleId().equals(currentAuditRoleId) && nextIndex < len) {
                        roleId = settings.get(nextIndex).getRoleId();
                    }
                }
                qualificationTransferApprovalFlow.setNextAuditRoleId(roleId);
                auditStatus = auditStatus + "审批通过";
            } else {
                auditStatus = auditStatus + "审批不通过";
            }
            qualificationTransferApprovalFlow.setAuditStatus(auditStatus);
            mapper.updateApplicationStatusById(qualificationTransferTransferId, auditStatus);
            int rows = 0;
            if (latestRecord != null) {
                if (latestRecord.getNextAuditRoleId() == null) {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
                if (latestRecord.getNextAuditRoleId().equals(currentAuditRoleId)) {
                    rows = approvalFlowMapper.insertAuditRecord(qualificationTransferApprovalFlow);
                } else {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
            } else {
                rows = approvalFlowMapper.insertAuditRecord(qualificationTransferApprovalFlow);
            }
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_AUDIT_RECORD);
        }
    }

    @Override
    public ListPages<QualificationTransferTransferDO> listTransferRecords(ListPages<QualificationTransferTransferDO> page, QualificationTransferTransferSearch search) {
        List<QualificationTransferTransferDO> list
                = mapper.listTransferRecords(page, search);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final QualificationTransferTransferDO transfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                    transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final QualificationTransferApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByQualificationTransferTransferId(transfer.getId());
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
