package com.industry.bean.request;

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
@ApiModel(value="QualificationTransferOrderDO对象", description="资质转让订单表")
public class QualificationTransferOrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资质转让订单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质转让id")
    @TableField("qualification_transfer_id")
    private Integer qualificationTransferId;

    @ApiModelProperty(value = "转让意向客户id")
    @TableField("transfer_customer_id")
    private Integer transferCustomerId;

    @ApiModelProperty(value = "成交金额")
    @TableField("transaction_amount")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "付款方式")
    @TableField("payment_way")
    private String paymentWay;

}
