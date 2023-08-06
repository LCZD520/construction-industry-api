package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 人才转账表
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_transfer")
@ApiModel(value = "TalentTransferDO对象", description = "人才转账表")
public class TalentTransferDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人才转账主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才主键id")
    @TableField("talent_id")
    private Integer talentId;

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
    private BigDecimal transferAmount;

    @ApiModelProperty(value = "款项用途")
    @TableField("funds_purpose")
    private Integer fundsPurpose;

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

    @ApiModelProperty(value = "转账审批流水")
    @TableField(exist = false)
    private List<TalentApprovalFlowDO> listTalentApprovalFlows;

    @ApiModelProperty(value = "人才")
    @TableField(exist = false)
    private OrderSelectedTalentDO talent;

    @ApiModelProperty(value = "当前审批角色id")
    @TableField(exist = false)
    private Integer currentAuditRoleId;

    @ApiModelProperty(value = "人才转账记录")
    @TableField(exist = false)
    private List<TalentTransferDO> listTalentTransfers;

    @ApiModelProperty(value = "人才入账记录")
    @TableField(exist = false)
    private List<TalentEntryRecordDO> listTalentEntryRecords;

    @ApiModelProperty(value = "人才入账")
    @TableField(exist = false)
    private List<TalentEntryDO> listTalentEntrys;

    @ApiModelProperty(value = "订单")
    @TableField(exist = false)
    private TalentOrderDO talentOrder;
}
