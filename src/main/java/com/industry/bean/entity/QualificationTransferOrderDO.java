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

/**
 * <p>
 * 资质转让订单表
 * </p>
 *
 * @author lc
 * @since 2022-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_order")
@ApiModel(value = "QualificationTransferOrderDO对象", description = "资质转让订单表")
public class QualificationTransferOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资质转让订单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "资质转让id")
    @TableField("qualification_transfer_id")
    private Integer qualificationTransferId;

    @ApiModelProperty(value = "转让意向客户id")
    @TableField("transfer_customer_id")
    private Integer transferCustomerId;

    @ApiModelProperty(value = "资质转让录入人id")
    @TableField("transfer_customer_id")
    private Integer qualificationTransferCreatorId;

    @ApiModelProperty(value = "资质收购录入人id")
    @TableField("transfer_customer_id")
    private Integer qualificationAcquisitionCreatorId;

    @ApiModelProperty(value = "成交金额")
    @TableField("transaction_amount")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "付款方式")
    @TableField("payment_way")
    private String paymentWay;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

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

    @ApiModelProperty(value = "资质转让信息")
    private QualificationTransferDO qualificationTransferDO;

    @ApiModelProperty(value = "资质收购信息")
    private QualificationAcquisitionDO qualificationAcquisitionDO;

}
