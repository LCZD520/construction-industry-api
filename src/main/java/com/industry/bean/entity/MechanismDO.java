package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 机构表
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_mechanism")
@ApiModel(value = "Mechanism对象", description = "机构表")
public class MechanismDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构主键id")
    @TableId(value = "mechanism_id", type = IdType.AUTO)
    private Integer mechanismId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "父机构")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "机构名称")
    @TableField("mechanism_name")
    private String mechanismName;

    @ApiModelProperty(value = "机构编号")
    @TableField("mechanism_number")
    private String mechanismNumber;

    @ApiModelProperty(value = "机构描述")
    @TableField("description")
    private String description;

    @TableField(exist = false)
    private List<MechanismDO> subListMechanisms;

}
