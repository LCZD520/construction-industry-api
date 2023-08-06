package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class TransferApprovalRequest {

    @ApiModelProperty(value = "人才转账id")
    private Integer talentTransferId;

    @ApiModelProperty(value = "审批结果")
    private Boolean auditResult;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

    @ApiModelProperty(value = "当前审批角色id")
    private Integer currentAuditRoleId;
}
