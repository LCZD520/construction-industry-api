package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业转账表
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_enterprise_transfer")
@ApiModel(value = "EnterpriseTransferDO对象", description = "企业转账表")
public class EnterpriseTransferDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业id")
    @TableField("enterprise_id")
    private Integer enterpriseId;

    @ApiModelProperty(value = "银行")
    @TableField("bank_name")
    private String bankName;

    @ApiModelProperty(value = "开户行")
    @TableField("open_bank")
    private String openBank;

    @ApiModelProperty(value = "户名")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "申请转账金额")
    @TableField("transfer_amount")
    private Integer transferAmount;

    @ApiModelProperty(value = "款项用途")
    @TableField("funds_purpose")
    private String fundsPurpose;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "已分配人才")
    @TableField(exist = false)
    private List<OrderSelectedTalentDO> listAssignedTalents;

    @ApiModelProperty(value = "转账审批记录")
    @TableField(exist = false)
    private List<EnterpriseApprovalFlowDO> listAuditRecords;

    @ApiModelProperty(value = "当前审批角色id")
    @TableField(exist = false)
    private Integer currentAuditRoleId;

    @ApiModelProperty(value = "企业")
    @TableField(exist = false)
    private EnterpriseDO enterprise;

    @ApiModelProperty(value = "企业名称")
    @TableField(exist = false)
    private String enterpriseName;

}
