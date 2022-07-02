package com.industry.entity;

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
 * 角色权限关联表
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_permission")
@ApiModel(value="RolePermission对象", description="角色权限关联表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色权限主键id")
    @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    @ApiModelProperty(value = "角色主键id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "权限主键id")
    @TableField("permission_id")
    private Integer permissionId;

    @ApiModelProperty(value = "是否可用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField("regenerator_id")
    private Integer regeneratorId;


}
