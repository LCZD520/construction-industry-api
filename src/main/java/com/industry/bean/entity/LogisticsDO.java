package com.industry.bean.entity;

import java.math.BigDecimal;

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
 * 后勤表
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_logistics")
@ApiModel(value = "LogisticsDO对象", description = "后勤表")
public class LogisticsDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才/企业主键id")
    @TableField("resource_id")
    private Integer resourceId;

    @ApiModelProperty(value = "客户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "后勤记录添加备注")
    @TableField("add_remark")
    private String addRemark;

    @ApiModelProperty(value = "后勤项目")
    @TableField("logistics_project_type")
    private Integer logisticsProjectType;

    @ApiModelProperty(value = "邮寄详细物品")
    @TableField("detail_item")
    private String detailItem;

    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "电话号码")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "收件人")
    @TableField("recipient")
    private String recipient;

    @ApiModelProperty(value = "后勤记录类型（1-人才，2-企业）")
    @TableField("logistics_type")
    private Integer logisticsType;

    @ApiModelProperty(value = "申请状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "是否确认")
    @TableField("is_confirm")
    private Boolean confirm;

    @ApiModelProperty(value = "后勤记录确认备注")
    @TableField("confirm_remark")
    private String confirmRemark;

    @ApiModelProperty(value = "后勤记录确认费用")
    @TableField("confirm_cost")
    private BigDecimal confirmCost;

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
