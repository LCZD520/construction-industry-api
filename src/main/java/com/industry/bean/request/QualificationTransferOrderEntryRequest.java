package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 资质转让订单入账请求体
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_order_entry")
@ApiModel(value = "QualificationTransferOrderEntryDO对象", description = "资质转让订单入账表")
public class QualificationTransferOrderEntryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @Null(message = "id必须为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质转让订单id")
    @NotNull(message = "资质转让订单id不能为空")
    @TableField("qualification_transfer_order_id")
    private Integer qualificationTransferOrderId;

    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = "订单编号不能为空")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "收购意向客户id")
    @NotNull(message = "收购意向客户id不能为空")
    @TableField("qualification_transfer_id")
    private Integer qualificationTransferId;

    @ApiModelProperty(value = "收购意向客户")
    @NotBlank(message = "收购意向客户不能为空")
    @TableField("transfer_customers")
    private String transferCustomers;

    @ApiModelProperty(value = "款项用途")
    @NotNull(message = "款项用途不能为空")
    @TableField("funds_purpose")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "转入金额")
    @NotNull(message = "转入金额不能为空")
    @TableField("entry_amount")
    private BigDecimal entryAmount;

    @ApiModelProperty(value = "转账方信息")
    @NotBlank(message = "转账方信息不能为空")
    @TableField("transferor_info")
    private String transferorInfo;

    @ApiModelProperty(value = "公司账户id")
    @NotNull(message = "公司账户id不能为空")
    @TableField("enterprise_account_id")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "银行名称")
    @TableField("bank_name")
    private Integer bankName;

    @ApiModelProperty(value = "银行卡号")
    @NotBlank(message = "银行卡号不能为空")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "户名")
    @NotBlank(message = "户名不能为空")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "开户行")
    @NotBlank(message = "开户行不能为空")
    @TableField("opening_bank")
    private String openingBank;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty(value = "转账方式")
    @NotNull(message = "转账方式不能为空")
    @TableField("transfer_way")
    private Integer transferWay;

    @ApiModelProperty(value = "到账日期")
    @NotNull(message = "到账日期不能为空")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("receipt_date")
    private Date receiptDate;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "资质转让录入人id")
    @NotNull(message = "资质转让录入人id不能为空")
    @TableField("qualification_transfer_creator_id")
    private Integer qualificationTransferCreatorId;

}
