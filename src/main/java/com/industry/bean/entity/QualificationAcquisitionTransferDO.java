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
 * 资质收购转账表
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_acquisition_transfer")
@ApiModel(value = "QualificationAcquisitionTransferDO对象", description = "资质收购转账表")
public class QualificationAcquisitionTransferDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人才转账主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质收购id")
    @TableField("qualification_acquisition_id")
    private Integer qualificationAcquisitionId;

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

    @ApiModelProperty(value = "转让意向客户")
    @TableField(exist = false)
    private String transferCustomers;

    @ApiModelProperty(value = "资质转让订单")
    @TableField(exist = false)
    private QualificationTransferOrderDO qualificationTransferOrder;

    @ApiModelProperty(value = "收购意向客户")
    @TableField(exist = false)
    private String acquisitionIntendedCustomer;

    @ApiModelProperty(value = "当前审批角色id")
    @TableField(exist = false)
    private Integer currentAuditRoleId;

    @ApiModelProperty(value = "资质转让录入人")
    @TableField(exist = false)
    private Integer qualificationTransferCreatorId;

    @ApiModelProperty(value = "资质收购转账申请流水")
    @TableField(exist = false)
    private List<QualificationAcquisitionApprovalFlowDO> listTransferApproveFlows;


}
