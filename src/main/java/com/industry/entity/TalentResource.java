package com.industry.entity;

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
 * 人才资源表
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_resource")
@ApiModel(value="TalentResource对象", description="人才资源表")
public class TalentResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "姓名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "社保")
    @TableField("social_security")
    private String socialSecurity;

    @ApiModelProperty(value = "电话号码")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    @TableField("qq_number")
    private String qqNumber;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private Integer education;

    @ApiModelProperty(value = "职称")
    @TableField("title")
    private Integer title;

    @ApiModelProperty(value = "三类人员")
    @TableField("class_three_personnel")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "招标出场")
    @TableField("tender_exit")
    private Integer tenderExit;

    @ApiModelProperty(value = "证书状态")
    @TableField("certificate_status")
    private Integer certificateStatus;

    @ApiModelProperty(value = "客户类型")
    @TableField("talent_type")
    private Integer talentType;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "数量")
    @TableField("price_number")
    private Integer priceNumber;

    @ApiModelProperty(value = "价格数量单位")
    @TableField("number_unit")
    private Integer numberUnit;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty(value = "创建人名字")
    @TableField("creator_name")
    private String creatorName;

    @ApiModelProperty(value = "更新人id")
    @TableField("regenerator_id")
    private Integer regeneratorId;

    @ApiModelProperty(value = "更新人名字")
    @TableField("regenerator_name")
    private String regeneratorName;

    @ApiModelProperty(value = "人才要求")
    @TableField("talent_requirement")
    private String talentRequirement;

    @ApiModelProperty(value = "跟进情况")
    @TableField("follow_up_situation")
    private String followUpSituation;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "资源是否共享")
    @TableField("is_shared")
    private Boolean shared;


}
