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
 * 角色表
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
@ApiModel(value = "Role对象", description = "角色表")
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

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

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "数据权限")
    @TableField("data_permission")
    private Integer dataPermission;

    @ApiModelProperty(value = "角色描述")
    @TableField("description")
    private String description;


}
