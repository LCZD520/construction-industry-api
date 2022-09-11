package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.request.EnterpriseDemandRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 企业表
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_enterprise")
@ApiModel(value = "EnterpriseDO对象", description = "企业表")
public class EnterpriseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业名称")
    @TableField("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "企业资质")
    @TableField("enterprise_qualifications")
    private Integer enterpriseQualifications;

    @ApiModelProperty(value = "地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "联系地址")
    @TableField("contact_address")
    private String contactAddress;

    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    @TableField("qq_number")
    private String qqNumber;

    @ApiModelProperty(value = "企业状态")
    @TableField("enterprise_status")
    private Integer enterpriseStatus;

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

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "企业需求")
    @TableField(exist = false)
    private List<EnterpriseDemandDO> listEnterpriseDemands;
}
