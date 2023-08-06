package com.industry.bean.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 职称评审人员信息表
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_title_assessor")
@ApiModel(value = "TitleAssessorDO对象", description = "职称评审人员信息表")
public class ExcelTitle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职称评审id")
    @TableField("title_evaluation_id")
    private Integer titleEvaluationId;

    @ApiModelProperty(value = "职称评审订单id")
    @TableField("title_evaluation_order_id")
    private Integer titleEvaluationOrderId;

    @ApiModelProperty(value = "姓名")
    @TableField("full_name")
    @ExcelProperty(value = "姓名", index = 0)
    private String fullName;

    @ApiModelProperty(value = "性别")
    @ExcelProperty(value = "性别", index = 1)
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证")
    @ExcelProperty(value = "身份证", index = 2)
    @TableField("identity_card")
    private String identityCard;

    @ApiModelProperty(value = "联系电话")
    @ExcelProperty(value = "联系电话", index = 3)
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "学历")
    @ExcelProperty(value = "学历", index = 4)
    @TableField("education")
    private String education;

    @ApiModelProperty(value = "毕业专业")
    @ExcelProperty(value = "毕业专业", index = 5)
    @TableField("graduation_major")
    private String graduationMajor;

    @ApiModelProperty(value = "评审专业")
    @ExcelProperty(value = "评审专业", index = 6)
    @TableField("evaluation_major")
    private String evaluationMajor;

    @ApiModelProperty(value = "评审职称")
    @ExcelProperty(value = "评审职称", index = 7)
    @TableField("title_evaluation")
    private String titleEvaluation;

    @ApiModelProperty(value = "证书性质")
    @ExcelProperty(value = "证书性质", index = 8)
    @TableField("certificate_nature")
    private String certificateNature;

    @ApiModelProperty(value = "鉴定方式")
    @ExcelProperty(value = "鉴定方式", index = 9)
    @TableField("appraisal_way")
    private String appraisalWay;

    @ApiModelProperty(value = "代办金额")
    @ExcelProperty(value = "代办金额", index = 10)
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

}
