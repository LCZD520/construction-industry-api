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
 * 资质代办-建造师
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_agency_constructor")
@ApiModel(value="QualificationAgencyConstructorDO对象", description="资质代办-建造师")
public class QualificationAgencyConstructorDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质代办id")
    @TableField("qualification_agency_id")
    private Integer qualificationAgencyId;

    @ApiModelProperty(value = "级别专业-初始")
    @TableField("level_major_initial_conversion")
    private String levelMajorInitialConversion;

    @ApiModelProperty(value = "招标出场")
    @TableField("tender_exit")
    private Integer tenderExit;

    @ApiModelProperty(value = "人数")
    @TableField("demand_number")
    private Integer demandNumber;

    @ApiModelProperty(value = "企业出价")
    @TableField("talent_price")
    private Integer talentPrice;

    @TableField("talent_price_number")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "单位")
    @TableField("number_unit")
    private Integer numberUnit;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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


}
