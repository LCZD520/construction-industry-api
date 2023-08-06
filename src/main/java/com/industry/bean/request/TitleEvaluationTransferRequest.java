package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.entity.PictureTempDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 职称评审转账表
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_title_evaluation_transfer")
@ApiModel(value="TitleEvaluationTransferDO对象", description="职称评审转账表")
public class TitleEvaluationTransferRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职称评审转账主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职称评审订单id")
    @NotNull(message = "职称评审订单id不能为空")
    @TableField("title_evaluation_order_id")
    private Integer titleEvaluationOrderId;

    @ApiModelProperty(value = "客户名称")
    @NotBlank(message = "客户名称不能为空")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = "订单编号不能为空")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "银行")
    @NotBlank(message = "银行不能为空")
    @TableField("bank_name")
    private String bankName;

    @ApiModelProperty(value = "开户行")
    @NotBlank(message = "开户行不能为空")
    @TableField("open_bank")
    private String openBank;

    @ApiModelProperty(value = "户名")
    @NotBlank(message = "户名不能为空")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "银行卡号")
    @NotBlank(message = "银行卡号不能为空")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "申请转账金额")
    @NotNull(message = "申请转账金额不能为空")
    @TableField("transfer_amount")
    private BigDecimal transferAmount;

    @ApiModelProperty(value = "款项用途")
    @NotNull(message = "款项用途不能为空")
    @TableField("funds_purpose")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "转账图片列表")
    List<PictureTempDO> listImages;


}
