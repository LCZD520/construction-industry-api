package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lc
 * @date 2022/7/15
 */
@Data
public class QualificationAcquisitionRequest {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "转让意向客户")
    private String transferCustomers;

    @ApiModelProperty(value = "所在地区")
    private String area;

    @ApiModelProperty(value = "资质类别及等级")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String categoryAndGrade;

    @ApiModelProperty(value = "收购金额")
    private BigDecimal acquisitionAmount;

    @ApiModelProperty(value = "安全许可证")
    private Boolean safetyPermit;

    @ApiModelProperty(value = "在建工程")
    private Boolean constructionProgress;

    @ApiModelProperty(value = "资质人员")
    private Boolean qualifiedPersonnel;

    @ApiModelProperty(value = "收购日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date acquisitionDate;
}
