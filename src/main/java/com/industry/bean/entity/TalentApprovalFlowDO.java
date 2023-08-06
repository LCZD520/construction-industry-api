package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人才转账审批流水
 * </p>
 *
 * @author lc
 * @since 2022-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_approval_flow")
@ApiModel(value = "TalentApprovalFlowDO对象", description = "人才转账审批流水")
public class TalentApprovalFlowDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才转账id")
    @TableField("talent_transfer_id")
    private Integer talentTransferId;

    @ApiModelProperty(value = "审批状态")
    @TableField("audit_status")
    private String auditStatus;

    @ApiModelProperty(value = "审批结果")
    @TableField("audit_result")
    private Boolean auditResult;

    @ApiModelProperty(value = "审批意见")
    @TableField("approval_opinion")
    private String approvalOpinion;

    @ApiModelProperty(value = "下级审批节点角色id")
    @TableField("next_audit_role_id")
    private Integer nextAuditRoleId;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;


}
