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
 * 人才证件去向流水表
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_certificates_whereabouts_flow")
@ApiModel(value = "TalentCertificatesWhereaboutsFlowDO对象", description = "人才证件去向流水表")
public class TalentCertificatesWhereaboutsFlowDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才证件id")
    @TableField("talent_certificates_id")
    private Integer talentCertificatesId;

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

    @ApiModelProperty(value = "去向备注")
    @TableField("remark")
    private String remark;


}
