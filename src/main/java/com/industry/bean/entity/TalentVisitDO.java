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
 * 人才回访表
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_visit")
@ApiModel(value="TalentVisitDO对象", description="人才回访表")
public class TalentVisitDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才主键id")
    @TableField("talent_id")
    private Integer talentId;

    @ApiModelProperty(value = "回访内容")
    @TableField("visit_content")
    private String visitContent;

    @ApiModelProperty(value = "是否需要再次回访")
    @TableField("is_need_visit_again")
    private Boolean needVisitAgain;

    @ApiModelProperty(value = "下次回访日期")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("visit_again_time")
    private Date visitAgainTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;


}
