package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class EnterpriseTransferApprovalRequest {

    @ApiModelProperty(value = "企业转账id")
    private Integer enterpriseTransferId;

    @ApiModelProperty(value = "审批结果")
    private Boolean auditResult;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

    @ApiModelProperty(value = "当前审批角色id")
    private Integer currentAuditRoleId;
}
