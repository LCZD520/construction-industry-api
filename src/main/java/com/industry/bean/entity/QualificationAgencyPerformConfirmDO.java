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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>
 * 资质代办执行确认表
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_agency_perform_confirm")
@ApiModel(value = "QualificationAgencyPerformConfirmDO对象", description = "资质代办执行确认表")
public class QualificationAgencyPerformConfirmDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @Null(message = "主键id必须为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资质代办id")
    @NotNull(message = "资质代办id不能为空")
    @TableField("qualification_agency_id")
    private Integer qualificationAgencyId;

    @ApiModelProperty(value = "执行确认结果")
    @NotNull(message = "执行确认结果不能为空")
    @TableField("confirm_result")
    private Boolean confirmResult;

    @ApiModelProperty(value = "执行确认意见")
    @TableField("confirm_opinion")
    private String confirmOpinion;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;


}
