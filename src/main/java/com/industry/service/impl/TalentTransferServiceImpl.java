package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ApprovalSettingDO;
import com.industry.bean.entity.RoleDO;
import com.industry.bean.entity.TalentApprovalFlowDO;
import com.industry.bean.entity.TalentTransferDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.mapper.TalentApprovalFlowMapper;
import com.industry.mapper.TalentEntryRecordMapper;
import com.industry.mapper.TalentTransferMapper;
import com.industry.service.TalentTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Service
public class TalentTransferServiceImpl extends ServiceImpl<TalentTransferMapper, TalentTransferDO> implements TalentTransferService {

    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private TalentTransferMapper mapper;

    private TalentApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    private TalentEntryRecordMapper recordMapper;

    @Autowired
    public void setResultEntity(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(TalentTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setApprovalFlowMapper(TalentApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Autowired
    public void setRecordMapper(TalentEntryRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public int insert(TalentTransferDO talentTransferDO) {
        return mapper.insert(talentTransferDO);
    }

    @Override
    public List<TalentTransferDO> listTalentTransfers(Integer id) {
        return mapper.listTalentTransfers(id);
    }

    @Override
    public int deleteById(Integer id) {

        return mapper.deleteById(id);
    }

    @Override
    public TalentTransferDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public ListPages<TalentTransferDO> listAllTalentTransfers(ListPages<TalentTransferDO> page) {
        final List<TalentTransferDO> list = mapper.listAllTalentTransfers(page);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final TalentTransferDO talentTransfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(talentTransfer.getApplicationStatus())) {
                    talentTransfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final TalentApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByTalentTransferId(talentTransfer.getId());
                if (latestRecord != null) {
                    talentTransfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
                }
            }
        }
        page.setList(list);
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ResultEntity audit(TalentApprovalFlowDO talentApprovalFlow, Integer currentAuditRoleId) {
        final Integer talentTransferId = talentApprovalFlow.getTalentTransferId();
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
            final TalentApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByTalentTransferId(talentTransferId);
            final Boolean auditResult = talentApprovalFlow.getAuditResult();
            String auditStatus = roleName;
            if (auditResult) {
                Integer roleId = null;
                for (int i = 0, len = settings.size(); i < len; i++) {
                    int nextIndex = i + 1;
                    if (settings.get(i).getRoleId().equals(currentAuditRoleId) && nextIndex < len) {
                        roleId = settings.get(nextIndex).getRoleId();
                    }
                }
                talentApprovalFlow.setNextAuditRoleId(roleId);
                auditStatus = auditStatus + "审批通过";
            } else {
                auditStatus = auditStatus + "审批不通过";
            }
            int rows;
            if (latestRecord != null) {
                if (latestRecord.getNextAuditRoleId() == null) {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
                if (latestRecord.getNextAuditRoleId().equals(currentAuditRoleId)) {
                    talentApprovalFlow.setAuditStatus(auditStatus);
                    mapper.updateApplicationStatusById(talentTransferId, auditStatus);
                    rows = approvalFlowMapper.insertAuditRecord(talentApprovalFlow);
                } else {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
            } else {
                talentApprovalFlow.setAuditStatus(auditStatus);
                mapper.updateApplicationStatusById(talentTransferId, auditStatus);
                rows = approvalFlowMapper.insertAuditRecord(talentApprovalFlow);
            }
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_AUDIT_RECORD);
        }
    }

    @Override
    public TalentTransferDO getAuditDetailById(Integer id) {
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        final TalentTransferDO talentTransfer = mapper.getAuditDetailById(id);
        if (INITIAL_APPLICATION_STATUS.equals(talentTransfer.getApplicationStatus())) {
            talentTransfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
        }
        final TalentApprovalFlowDO latestRecord
                = approvalFlowMapper.getLatestRecordByTalentTransferId(talentTransfer.getId());
        if (latestRecord != null) {
            talentTransfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
        }
        return talentTransfer;
    }

    @Override
    public ResultEntity generateAchievement(Integer id) {
        synchronized (this) {
            final int rows = mapper.selectByIdAndStatus(id, "出纳审批通过");
            if (rows < 1) {
                return result.failure(ResultCodeEnum.FAIL_TALENT_TRANSFER_RECORD_NO_PASS);
            }
            final int rows2 = recordMapper.selectByIdAndStatus(id, 2);
            if (rows2 < 1) {
                return result.failure(ResultCodeEnum.FAIL_TALENT_ENTRY_RECORD_NO_PASS);
            }
            return null;
        }
    }
}
