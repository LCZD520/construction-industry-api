package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.industry.bean.entity.PictureTempDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
public class EducationPromotionAssessorRequest {
    @ApiModelProperty(value = "学历提升人员信息id")
    @Null(message = "学历提升人员信息id必须为空", groups = Insert.class)
    @NotNull(message = "学历提升人员信息id必须不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "学历提升主键id")
    @TableField("education_promotion_id")
    private Integer educationPromotionId;

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

    @ApiModelProperty(value = "原始学历")
    @TableField("original_education")
    private String originalEducation;

    @ApiModelProperty(value = "申报学校")
    @TableField("application_school")
    private String applicationSchool;

    @ApiModelProperty(value = "学制")
    @TableField("educational_system")
    private String educationalSystem;

    @ApiModelProperty(value = "专业")
    @TableField("major")
    private String major;

    @ApiModelProperty(value = "提升费用")
    @TableField("promotion_fee")
    private BigDecimal promotionFee;

    @ApiModelProperty(value = "提升学历")
    @TableField("improve_education")
    private String improveEducation;

    @ApiModelProperty(value = "代办金额")
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

    private List<PictureTempDO> listImages;
}
