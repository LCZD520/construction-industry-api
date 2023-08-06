package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 三类人员转账表
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_class_three_personnel_transfer")
@ApiModel(value="ClassThreePersonnelTransferDO对象", description="三类人员转账表")
public class ClassThreePersonnelTransferRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "三类人员主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "三类人员订单id")
    @TableField("class_three_personnel_order_id")
    private Integer classThreePersonnelOrderId;

    @ApiModelProperty(value = "客户名称")
    @NotBlank(message = "客户名称不能为空")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = "订单编号不能为空")
    @TableField("orderno")
    private String orderno;

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

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


}
