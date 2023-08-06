package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.request.EnterpriseTransferRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.mapper.EnterpriseApprovalFlowMapper;
import com.industry.mapper.EnterpriseTransferMapper;
import com.industry.service.EnterpriseTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 企业转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Slf4j
@Service
public class EnterpriseTransferServiceImpl extends ServiceImpl<EnterpriseTransferMapper, EnterpriseTransferDO> implements EnterpriseTransferService {
    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private EnterpriseApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    private EnterpriseTransferMapper mapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(EnterpriseTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Autowired
    public void setApprovalFlowMapper(EnterpriseApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Override
    public List<OrderSelectedTalentDO> getWaitAssignTalents(Integer id) {
        return mapper.getWaitAssignTalents(id);
    }

    @Override
    public ResultEntity insert(EnterpriseTransferRequest request) {
        EnterpriseTransferDO transfer = new EnterpriseTransferDO();
        transfer.setAccountName(request.getAccountName());
        transfer.setEnterpriseId(request.getEnterpriseId());
        transfer.setTransferAmount(request.getTransferAmount());
        transfer.setBankName(request.getBankName());
        transfer.setBankCardNo(request.getBankCardNo());
        transfer.setOpenBank(request.getOpenBank());
        transfer.setFundsPurpose(request.getFundsPurpose());
        transfer.setRemark(request.getRemark());
        List<Integer> orderSelectedTalentIds = request.getOrderSelectedTalentIds();

        synchronized (this) {
            List<Integer> waitAssignTalentIds = mapper.getWaitAssignTalentIds(request.getEnterpriseId());
            List<Integer> lis = new ArrayList<>();
            for (Integer id : orderSelectedTalentIds) {
                if (!waitAssignTalentIds.contains(id)) {
                    lis.add(id);
                }
            }
            if (lis.size() > 0) {
                List<String> list = mapper.selectFullNameByIds(lis);
                return result.failure(ResultCodeEnum.FAIL_APPLY_EXISTED_TALENT, list);
            } else {
                mapper.insert(transfer);
                mapper.updateInfoByIds(transfer.getId(), orderSelectedTalentIds);
                return result.success(ResultCodeEnum.SUCCESS_APPLY_TRANSFER);
            }
        }
    }

    @Override
    public List<EnterpriseTransferDO> getListTransferRecords(Integer id) {
        return mapper.getListTransferRecords(id);
    }

    @Override
    public EnterpriseTransferDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public ListPages<EnterpriseTransferDO> listTransferRecords(ListPages<EnterpriseTransferDO> page) {
        final List<EnterpriseTransferDO> list = mapper.listAllEnterpriseTransfers(page);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final EnterpriseTransferDO enterpriseTransfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(enterpriseTransfer.getApplicationStatus())) {
                    enterpriseTransfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final EnterpriseApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByEnterpriseTransferId(enterpriseTransfer.getId());
                if (latestRecord != null) {
                    enterpriseTransfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
                }
            }
        }
        page.setList(list);
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ResultEntity audit(EnterpriseApprovalFlowDO enterpriseApprovalFlow, Integer currentAuditRoleId) {
        final Integer enterpriseTransferId = enterpriseApprovalFlow.getEnterpriseTransferId();
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
            final EnterpriseApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByEnterpriseTransferId(enterpriseTransferId);
            final Boolean auditResult = enterpriseApprovalFlow.getAuditResult();
            String auditStatus = roleName;
            if (auditResult) {
                Integer roleId = null;
                for (int i = 0, len = settings.size(); i < len; i++) {
                    int nextIndex = i + 1;
                    if (settings.get(i).getRoleId().equals(currentAuditRoleId) && nextIndex < len) {
                        roleId = settings.get(nextIndex).getRoleId();
                    }
                }
                enterpriseApprovalFlow.setNextAuditRoleId(roleId);
                auditStatus = auditStatus + "审批通过";
            } else {
                auditStatus = auditStatus + "审批不通过";
            }
            enterpriseApprovalFlow.setAuditStatus(auditStatus);
            mapper.updateApplicationStatusById(enterpriseTransferId, auditStatus);
            int rows = 0;
            if (latestRecord != null) {
                if (latestRecord.getNextAuditRoleId() == null) {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
                if (latestRecord.getNextAuditRoleId().equals(currentAuditRoleId)) {
                    rows = approvalFlowMapper.insertAuditRecord(enterpriseApprovalFlow);
                } else {
                    return result.failure(ResultCodeEnum.FAIL_EXIST_LATEST_AUDIT_RECORD);
                }
            } else {
                rows = approvalFlowMapper.insertAuditRecord(enterpriseApprovalFlow);
            }
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_AUDIT_RECORD);
            }
            return result.failure(ResultCodeEnum.FAIL_AUDIT_RECORD);
        }
    }

    @Override
    public EnterpriseTransferDO getAuditDetailById(Integer id) {
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        final EnterpriseTransferDO enterpriseTransfer = mapper.getAuditDetailById(id);
        if (INITIAL_APPLICATION_STATUS.equals(enterpriseTransfer.getApplicationStatus())) {
            enterpriseTransfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
        }
        final EnterpriseApprovalFlowDO latestRecord
                = approvalFlowMapper.getLatestRecordByEnterpriseTransferId(enterpriseTransfer.getId());
        if (latestRecord != null) {
            enterpriseTransfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
        }
        return enterpriseTransfer;
    }
}
