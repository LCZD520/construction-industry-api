package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 企业需求表
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_enterprise_demand")
@ApiModel(value="EnterpriseDemandDO对象", description="企业需求表")
public class EnterpriseDemandDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业主键id")
    @TableField("enterprise_id")
    private Integer enterpriseId;

    @ApiModelProperty(value = "三类人员")
    @TableField("class_three_personnel")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "招标出场")
    @TableField("tender_exit")
    private Integer tenderExit;

    @ApiModelProperty(value = "需求人数")
    @TableField("demand_number")
    private Integer demandNumber;

    @ApiModelProperty(value = "企业出价")
    @TableField("enterprise_offer")
    private Integer enterpriseOffer;

    @ApiModelProperty(value = "企业出价数")
    @TableField("enterprise_offer_number")
    private Integer enterpriseOfferNumber;

    @ApiModelProperty(value = "企业出价单位")
    @TableField("enterprise_offer_unit")
    private Integer enterpriseOfferUnit;

    @ApiModelProperty(value = "市场开发费")
    @TableField("market_development_fee")
    private Integer marketDevelopmentFee;

    @ApiModelProperty(value = "市场开发费单位")
    @TableField("market_development_fee_unit")
    private Integer marketDevelopmentFeeUnit;

    @ApiModelProperty(value = "职称")
    @TableField("title")
    private Integer title;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private Integer education;

    @ApiModelProperty(value = "级别专业-初始")
    @TableField(value = "level_major_initial_conversion")
    private String levelMajorInitialConversion;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
