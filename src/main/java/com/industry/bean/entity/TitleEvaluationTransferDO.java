package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TitleEvaluationTransferDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职称评审转账主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "职称评审订单id")
    @NotNull(message = "职称评审订单id不能为空")
    @TableField("title_evaluation_order_id")
    private Integer titleEvaluationOrderId;

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

    @ApiModelProperty(value = "转账审核记录")
    @TableField(exist = false)
    List<TitleEvaluationApprovalFlowDO> listAuditRecords;

    @ApiModelProperty(value = "转账记录")
    @TableField(exist = false)
    List<TitleEvaluationTransferDO> listTransferRecords;

    @ApiModelProperty(value = "转账图片列表")
    @TableField(exist = false)
    List<PictureTempDO> listImages;

    @ApiModelProperty(value = "当前审批角色id")
    @TableField(exist = false)
    private Integer currentAuditRoleId;
}
