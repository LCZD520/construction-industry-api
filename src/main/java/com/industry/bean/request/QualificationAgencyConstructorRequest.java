package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lc
 * @date 2022/7/17
 */
@Data
public class QualificationAgencyConstructorRequest {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "资质代办id")
    private Integer qualificationAgencyId;

    @ApiModelProperty(value = "级别专业-初始")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String levelMajorInitialConversion;

    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @ApiModelProperty(value = "人数")
    private Integer demandNumber;

    @ApiModelProperty(value = "企业出价")
    private Integer talentPrice;

    @ApiModelProperty(value = "企业出价数量")
    @TableField("talent_price_number")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "单位")
    private Integer numberUnit;

    @ApiModelProperty(value = "备注")
    private String remark;
}
