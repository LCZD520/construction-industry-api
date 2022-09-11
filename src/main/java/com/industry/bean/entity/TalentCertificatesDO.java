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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 人才证件表
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_certificates")
@ApiModel(value="TalentCertificatesDO对象", description="人才证件表")
public class TalentCertificatesDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "talent_certificates_id", type = IdType.AUTO)
    private Integer talentCertificatesId;

    @ApiModelProperty(value = "人才主键id")
    @TableField("talent_id")
    private Integer talentId;

    @ApiModelProperty(value = "证件所在分支")
    @TableField("mechanism_id")
    private Integer mechanismId;

    @ApiModelProperty(value = "证件类型")
    @TableField("certificates_type")
    private Integer certificatesType;

    @ApiModelProperty(value = "证件去向")
    @TableField("certificates_whereabouts")
    private Integer certificatesWhereabouts;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    public TalentCertificatesDO(Integer talentId, Integer mechanismId, Integer certificatesType, Integer certificatesWhereabouts, String remark) {
        this.talentId = talentId;
        this.mechanismId = mechanismId;
        this.certificatesType = certificatesType;
        this.certificatesWhereabouts = certificatesWhereabouts;
        this.remark = remark;
    }
}
