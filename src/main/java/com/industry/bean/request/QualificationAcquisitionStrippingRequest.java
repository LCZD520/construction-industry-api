package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/15
 */
@Data
public class QualificationAcquisitionStrippingRequest {
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

    @ApiModelProperty(value = "已选资质类别及等级")
    private List<String> list;

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

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;
}
