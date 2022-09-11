package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业资源表
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_enterprise_resource")
@ApiModel(value="EnterpriseResourceDO对象", description="企业资源表")
public class EnterpriseResourceDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资源是否共享")
    @TableField("is_shared")
    private Boolean shared;

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

    @ApiModelProperty(value = "客户类型")
    @TableField("customer_type")
    private Integer customerType;

    @ApiModelProperty(value = "跟进情况")
    @TableField("follow_up_situation")
    private String followUpSituation;

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

    @ApiModelProperty(value = "企业需求")
    @TableField(exist = false)
    private List<EnterpriseResourceDemandDO> listEnterpriseDemands;


}
