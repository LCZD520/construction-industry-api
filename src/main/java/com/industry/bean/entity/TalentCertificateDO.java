package com.industry.bean.entity;

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
 * 人才证书表
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_certificate")
@ApiModel(value="TalentCertificate对象", description="人才证书表")
public class TalentCertificateDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人才证书主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才主键id")
    @TableField("talent_id")
    private Integer talentId;

    @ApiModelProperty(value = "级别-专业")
    @TableField("level_major")
    private String levelMajor;

    @ApiModelProperty(value = "初始转注")
    @TableField("initial_conversion")
    private Integer initialConversion;

    @ApiModelProperty(value = "发证时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("issue_cert_time")
    private Date issueCertTime;

    @ApiModelProperty(value = "继续教育时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("continuing_education_date")
    private Date continuingEducationDate;

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
