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
 * 权限表
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_permission")
@ApiModel(value="Permission对象", description="权限表")
public class PermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限主键id")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField("regenerator_id")
    private Integer regeneratorId;

    @ApiModelProperty(value = "机构名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "权限uri")
    @TableField("permission_uri")
    private String permissionUri;

    @ApiModelProperty(value = "父级id")
    @TableField("parent_id")
    private Integer parentId;

    @TableField(exist = false)
    private List<PermissionDO> subListPermissions = new ArrayList<>();

}
