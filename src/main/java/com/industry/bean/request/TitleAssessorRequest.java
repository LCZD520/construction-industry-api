package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.industry.bean.entity.PictureTempDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
public class TitleAssessorRequest {
    @ApiModelProperty(value = "职称评审人员信息主键id")
    @Null(message = "职称评审人员信息主键id必须为空", groups = Insert.class)
    @NotNull(message = "职称评审人员信息主键id必须不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "职称评审id")
    @TableField("title_evaluation_id")
    private Integer titleEvaluationId;

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

    private List<PictureTempDO> listImages;
}
