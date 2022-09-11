package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/17
 */
@Data
public class QualificationAgencyRequest {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "代办公司")
    private String agencyCompany;

    @ApiModelProperty(value = "支付方式")
    private String paymentWay;

    @ApiModelProperty(value = "代办资质")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String agencyQualification;

    @ApiModelProperty(value = "办理地区")
    private String area;

    @ApiModelProperty(value = "代办金额")
    private BigDecimal agencyAmount;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "建造师列表")
    private List<QualificationAgencyConstructorRequest> listConstructors;

    @ApiModelProperty(value = "工程师/三类人员/技术工种/列表")
    private List<QualificationAgencyOtherPersonRequest> listOtherPersons;
}
