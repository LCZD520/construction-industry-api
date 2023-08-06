package com.industry.bean.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资质转让订单入账表
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_order_entry")
@ApiModel(value="QualificationTransferOrderEntryDO对象", description="资质转让订单入账表")
public class QualificationTransferOrderEntryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质转让订单id")
    @TableField("qualification_transfer_order_id")
    private Integer qualificationTransferOrderId;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "收购意向客户id")
    @TableField("qualification_transfer_id")
    private Integer qualificationTransferId;

    @ApiModelProperty(value = "收购意向客户")
    @TableField("transfer_customers")
    private String transferCustomers;

    @ApiModelProperty(value = "款项用途")
    @TableField("funds_purpose")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "转入金额")
    @TableField("entry_amount")
    private BigDecimal entryAmount;

    @ApiModelProperty(value = "转账方信息")
    @TableField("transferor_info")
    private String transferorInfo;

    @ApiModelProperty(value = "公司账户id")
    @TableField("enterprise_account_id")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "银行名称")
    @TableField("bank_name")
    private Integer bankName;

    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "户名")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "开户行")
    @TableField("opening_bank")
    private String openingBank;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty(value = "转账方式")
    @TableField("transfer_way")
    private Integer transferWay;

    @ApiModelProperty(value = "到账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("receipt_date")
    private Date receiptDate;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "资质转让录入人id")
    @TableField("qualification_transfer_creator_id")
    private Integer qualificationTransferCreatorId;

    @ApiModelProperty(value = "申请人id")
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


}
