package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/9
 */
@Data
public class EnterpriseDemandRequest {
    @ApiModelProperty(value = "企业需求主键id")
    private Integer id;

    @NotNull(message = "企业主键id", groups = Insert.class)
    @ApiModelProperty(value = "企业主键id")
    private Integer enterpriseId;

    @NotNull(message = "企业主键id", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "三类人员")
    private Integer classThreePersonnel;

    @NotNull(message = "企业主键id", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @NotNull(message = "需求人数不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "需求人数")
    private Integer demandNumber;

    @NotNull(message = "企业出价不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业出价")
    private Integer enterpriseOffer;

    @NotNull(message = "请选择", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业出价数")
    private Integer enterpriseOfferNumber;

    @NotNull(message = "请选择", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业出价单位")
    private Integer enterpriseOfferUnit;

    @NotNull(message = "市场开发费不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "市场开发费")
    private Integer marketDevelopmentFee;

    @NotNull(message = "市场开发费单位不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "市场开发费单位")
    private Integer marketDevelopmentFeeUnit;

    @ApiModelProperty(value = "职称")
    private Integer title;

    @ApiModelProperty(value = "学历")
    private Integer education;

    @NotBlank(message = "级别专业-初始转注不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "级别专业-初始")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String levelMajorInitialConversion;
}
