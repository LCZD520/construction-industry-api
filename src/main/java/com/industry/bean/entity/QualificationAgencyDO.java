package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 资质代办表
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_agency")
@ApiModel(value = "QualificationAgencyDO对象", description = "资质代办表")
public class QualificationAgencyDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "代办公司")
    @TableField("agency_company")
    private String agencyCompany;

    @ApiModelProperty(value = "支付方式")
    @TableField("payment_way")
    private String paymentWay;

    @ApiModelProperty(value = "代办资质")
    @TableField("agency_qualification")
    private String agencyQualification;

    @ApiModelProperty(value = "办理地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "代办金额")
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

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

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "建造师列表")
    @TableField(exist = false)
    private List<QualificationAgencyConstructorDO> listConstructors;

    @ApiModelProperty(value = "工程师/三类人员/技术工种/列表")
    @TableField(exist = false)
    private List<QualificationAgencyOtherPersonDO> listOtherPersons;


}
