package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;
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
 * 菜单表
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class MenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单主键id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField("regenerator_id")
    private Integer regeneratorId;

    @ApiModelProperty(value = "父菜单id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单路由")
    @TableField("route")
    private String route;

    @ApiModelProperty(value = "菜单标题")
    @TableField("menu_title")
    private String menuTitle;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "菜单图标(element-ui)")
    @TableField("menu_icon")
    private String menuIcon;

    @TableField(exist = false)
    private List<MenuDO> subListMenus;

}
