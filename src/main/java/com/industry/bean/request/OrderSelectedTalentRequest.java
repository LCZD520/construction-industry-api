package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lc
 * @date 2022/7/24
 */
@Data
public class OrderSelectedTalentRequest {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "人才订单id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "人才id")
    private Integer talentId;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "人才名称")
    private String fullName;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "三类人员")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "级别-专业-初始")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String levelMajorInitialConversion;

    @ApiModelProperty(value = "人才价格")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "价格数量单位")
    private Integer numberUnit;

    @ApiModelProperty(value = "企业出价")
    private Integer enterpriseOffer;

    @ApiModelProperty(value = "企业出价数")
    private Integer enterpriseOfferNumber;

    @ApiModelProperty(value = "企业出价单位")
    private Integer enterpriseOfferUnit;

    @ApiModelProperty(value = "合同余额")
    private Integer contractBalance;

    @ApiModelProperty(value = "市场开发费")
    private Integer marketDevelopmentFee;

    @ApiModelProperty(value = "市场开发费单位")
    @TableField("market_development_fee_unit")
    private Integer marketDevelopmentFeeUnit;
}
