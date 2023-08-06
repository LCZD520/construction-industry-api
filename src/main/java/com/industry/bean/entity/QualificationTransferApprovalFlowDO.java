package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资质转让转账审批流水
 * </p>
 *
 * @author lc
 * @since 2022-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_approval_flow")
@ApiModel(value="QualificationTransferApprovalFlowDO对象", description="资质转让转账审批流水")
public class QualificationTransferApprovalFlowDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质转让转账id")
    @TableField("qualification_transfer_transfer_id")
    private Integer qualificationTransferTransferId;

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
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;


}
