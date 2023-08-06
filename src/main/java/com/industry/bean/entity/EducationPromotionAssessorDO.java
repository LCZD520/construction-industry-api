package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学历提升评审人员信息表
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_education_promotion_assessor")
@ApiModel(value = "EducationPromotionAssessorDO对象", description = "学历提升评审人员信息表")
public class EducationPromotionAssessorDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学历提升信息id")
    @Null(message = "学历提升信息id必须为空", groups = Insert.class)
    @NotNull(message = "学历提升信息id不能为空", groups = Update.class)
    private Integer id;


    @ApiModelProperty(value = "学历提升id")
    @NotNull(message = "学历提升id不能为空", groups = {Insert.class, Update.class})
    @TableField("education_promotion_id")
    private Integer educationPromotionId;

    @ApiModelProperty(value = "学历提升订单id")
    @TableField("education_promotion_order_id")
    private Integer educationPromotionOrderId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空", groups = {Insert.class, Update.class})
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空", groups = {Insert.class, Update.class})
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证")
    @NotBlank(message = "身份证不能为空", groups = {Insert.class, Update.class})
    @TableField("identity_card")
    private String identityCard;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "原始学历")
    @NotBlank(message = "原始学历不能为空", groups = {Insert.class, Update.class})
    @TableField("original_education")
    private String originalEducation;

    @ApiModelProperty(value = "申报学校")
    @NotBlank(message = "申报学校不能为空", groups = {Insert.class, Update.class})
    @TableField("application_school")
    private String applicationSchool;

    @ApiModelProperty(value = "学制")
    @NotBlank(message = "学制不能为空", groups = {Insert.class, Update.class})
    @TableField("educational_system")
    private String educationalSystem;

    @ApiModelProperty(value = "专业")
    @NotBlank(message = "专业不能为空", groups = {Insert.class, Update.class})
    @TableField("major")
    private String major;

    @ApiModelProperty(value = "提升费用")
    @TableField("promotion_fee")
    private BigDecimal promotionFee;

    @ApiModelProperty(value = "提升学历")
    @NotBlank(message = "提升学历不能为空", groups = {Insert.class, Update.class})
    @TableField("improve_education")
    private String improveEducation;

    @ApiModelProperty(value = "代办金额")
    @NotNull(message = "代办金额不能为空", groups = {Insert.class, Update.class})
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

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

    @ApiModelProperty(value = "学历提升图片")
    @TableField(exist = false)
    private List<PictureTempDO> listImages;

}
