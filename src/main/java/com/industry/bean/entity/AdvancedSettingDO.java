package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

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
 * 高级设置表
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_advanced_setting")
@ApiModel(value = "AdvancedSetting对象", description = "高级设置表")
public class AdvancedSettingDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "advanced_setting_id", type = IdType.AUTO)
    private Integer advancedSettingId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id",fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "配置名称")
    @TableField("config_name")
    private String configName;

    @ApiModelProperty(value = "配置代码")
    @TableField("config_code")
    private String configCode;

    @ApiModelProperty(value = "配置值")
    @TableField("config_value")
    private String configValue;

    @ApiModelProperty(value = "配置描述")
    @TableField("config_description")
    private String configDescription;


}
