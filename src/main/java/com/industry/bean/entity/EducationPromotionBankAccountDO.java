package com.industry.bean.entity;

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
 * 学历提升转账银行账户表
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_education_promotion_bank_account")
@ApiModel(value="EducationPromotionBankAccountDO对象", description="学历提升转账银行账户表")
public class EducationPromotionBankAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学历提升订单id")
    @TableField("education_promotion_order_id")
    private Integer educationPromotionOrderId;

    @ApiModelProperty(value = "银行")
    @TableField("bank_name")
    private Integer bankName;

    @ApiModelProperty(value = "开户行")
    @TableField("open_bank")
    private String openBank;

    @ApiModelProperty(value = "户名")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;


}
