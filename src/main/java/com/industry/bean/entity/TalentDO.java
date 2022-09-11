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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 人才表
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent")
@ApiModel(value="Talent对象", description="人才表")
public class TalentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人才主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @ApiModelProperty(value = "发证时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("issue_cert_time")
    private Date issueCertTime;

    @ApiModelProperty(value = "继续教育时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("continuing_education_date")
    private Date continuingEducationDate;

    @ApiModelProperty(value = "招标出场")
    @TableField("tender_exit")
    private Integer tenderExit;

    @ApiModelProperty(value = "证书状态")
    @TableField("certificate_status")
    private Integer certificateStatus;

    @ApiModelProperty(value = "人才状态")
    @TableField("talent_status")
    private Integer talentStatus;

    @ApiModelProperty(value = "人才价格")
    @TableField("talent_price")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    @TableField("talent_price_number")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "价格数量单位")
    @TableField("number_unit")
    private Integer numberUnit;

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

    @ApiModelProperty(value = "证书列表")
    @TableField(exist = false)
    private List<TalentCertificateDO> listCertificates;

}
