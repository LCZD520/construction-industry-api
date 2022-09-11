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
 * 人才订单已选人才表
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_selected_talent")
@ApiModel(value = "OrderSelectedTalentDO对象", description = "人才订单已选人才表")
public class OrderSelectedTalentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才订单id")
    @TableField("talent_order_id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "企业转账申请id")
    @TableField("enterprise_application_transfer_id")
    private Integer enterpriseApplicationTransferId;

    @ApiModelProperty(value = "企业名称")
    @TableField("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "人才id")
    private Integer talentId;

    @ApiModelProperty(value = "人才名称")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "三类人员")
    @TableField("class_three_personnel")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "级别-专业-初始")
    @TableField("level_major_initial_conversion")
    private String levelMajorInitialConversion;

    @ApiModelProperty(value = "人才价格")
    @TableField("talent_price")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    @TableField("talent_price_number")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "价格数量单位")
    @TableField("number_unit")
    private Integer numberUnit;

    @ApiModelProperty(value = "企业出价")
    @TableField("enterprise_offer")
    private Integer enterpriseOffer;

    @ApiModelProperty(value = "企业出价数")
    @TableField("enterprise_offer_number")
    private Integer enterpriseOfferNumber;

    @ApiModelProperty(value = "企业出价单位")
    @TableField("enterprise_offer_unit")
    private Integer enterpriseOfferUnit;

    @ApiModelProperty(value = "合同余额")
    @TableField("contract_balance")
    private Integer contractBalance;

    @ApiModelProperty(value = "市场开发费")
    @TableField("market_development_fee")
    private Integer marketDevelopmentFee;

    @ApiModelProperty(value = "市场开发费单位")
    @TableField("market_development_fee_unit")
    private Integer marketDevelopmentFeeUnit;

    @ApiModelProperty(value = "是否确认")
    @TableField("is_confirmed")
    private Boolean confirmed;

    @ApiModelProperty(value = "是否被分配")
    @TableField("is_confirmed")
    private Boolean assigned;

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

    @ApiModelProperty(value = "人才订单")
    @TableField(exist = false)
    private TalentOrderDO talentOrder;

}
