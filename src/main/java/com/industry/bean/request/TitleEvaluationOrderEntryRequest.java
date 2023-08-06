package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 职称评审订单入账表
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_title_evaluation_order_entry")
@ApiModel(value = "TitleEvaluationOrderEntryDO对象", description = "职称评审订单入账表")
public class TitleEvaluationOrderEntryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @Null(groups = Insert.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职称评审id")
    @NotNull
    @TableField("title_evaluation_id")
    private Integer titleEvaluationId;

    @ApiModelProperty(value = "职称评审订单id")
    @NotNull
    @TableField("title_evaluation_order_id")
    private Integer titleEvaluationOrderId;

    @ApiModelProperty(value = "订单编号")
    @NotBlank
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "客户名称")
    @NotBlank
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "款项用途")
    @NotNull
    @TableField("funds_purpose")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "转入金额")
    @NotNull
    @TableField("entry_amount")
    private BigDecimal entryAmount;

    @ApiModelProperty(value = "转账方信息")
    @NotBlank
    @TableField("transferor_info")
    private String transferorInfo;

    @ApiModelProperty(value = "公司账户id")
    @NotNull
    @TableField("enterprise_account_id")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "银行名称")
    @NotNull
    @TableField("bank_name")
    private Integer bankName;

    @ApiModelProperty(value = "银行卡号")
    @NotBlank
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "户名")
    @NotBlank
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "开户行")
    @NotBlank
    @TableField("opening_bank")
    private String openingBank;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty(value = "转账方式")
    @NotNull
    @TableField("transfer_way")
    private Integer transferWay;

    @ApiModelProperty(value = "到账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("receipt_date")
    private Date receiptDate;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "职称评审录入人id")
    @NotNull
    @TableField("title_evaluation_creator_id")
    private Integer titleEvaluationCreatorId;


}
