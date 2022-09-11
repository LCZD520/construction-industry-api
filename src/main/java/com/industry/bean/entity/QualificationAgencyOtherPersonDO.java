package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资质代办-其他人员
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_agency_other_person")
@ApiModel(value = "QualificationAgencyOtherPersonDO对象", description = "资质代办-其他人员")
public class QualificationAgencyOtherPersonDO implements Serializable {

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

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private Integer type;

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
