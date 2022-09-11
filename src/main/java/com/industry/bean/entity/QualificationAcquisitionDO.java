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
 * 资质收购表
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_acquisition")
@ApiModel(value="QualificationAcquisitionDO对象", description="资质收购表")
public class QualificationAcquisitionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "转让意向客户")
    @TableField("transfer_customers")
    private String transferCustomers;

    @ApiModelProperty(value = "所在地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "资质类别及等级")
    @TableField("category_and_grade")
    private String categoryAndGrade;

    @ApiModelProperty(value = "收购金额")
    @TableField("acquisition_amount")
    private BigDecimal acquisitionAmount;

    @ApiModelProperty(value = "安全许可证")
    @TableField("safety_permit")
    private Boolean safetyPermit;

    @ApiModelProperty(value = "在建工程")
    @TableField("construction_progress")
    private Boolean constructionProgress;

    @ApiModelProperty(value = "资质人员")
    @TableField("qualified_personnel")
    private Boolean qualifiedPersonnel;

    @ApiModelProperty(value = "收购日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("acquisition_date")
    private Date acquisitionDate;

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


}
