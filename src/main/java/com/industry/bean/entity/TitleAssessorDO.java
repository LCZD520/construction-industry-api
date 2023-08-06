package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.request.TitleAssessorRequest;
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
public class TitleAssessorDO implements Serializable {

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
    private String fullName;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证")
    @TableField("identity_card")
    private String identityCard;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private String education;

    @ApiModelProperty(value = "毕业专业")
    @TableField("graduation_major")
    private String graduationMajor;

    @ApiModelProperty(value = "评审专业")
    @TableField("evaluation_major")
    private String evaluationMajor;

    @ApiModelProperty(value = "评审职称")
    @TableField("title_evaluation")
    private String titleEvaluation;

    @ApiModelProperty(value = "证书性质")
    @TableField("certificate_nature")
    private String certificateNature;

    @ApiModelProperty(value = "代办金额")
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

    @ApiModelProperty(value = "评审费用")
    @TableField("evaluation_fee")
    private BigDecimal evaluationFee;

    @ApiModelProperty(value = "鉴定方式")
    @TableField("appraisal_way")
    private String appraisalWay;

    @ApiModelProperty(value = "评审状态")
    @TableField("evaluation_status")
    private Integer evaluationStatus;

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

    @ApiModelProperty(value = "职称评审人员图片")
    @TableField(exist = false)
    private List<PictureTempDO> listImages;


}
