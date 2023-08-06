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
 * 学历提升订单表
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_education_promotion_order")
@ApiModel(value = "EducationPromotionOrderDO对象", description = "学历提升订单表")
public class EducationPromotionOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "学历提升id")
    @TableField("education_promotion_id")
    private Integer educationPromotionId;

    @ApiModelProperty(value = "人员数")
    @TableField("assessor_num")
    private Integer assessorNum;

    @ApiModelProperty(value = "代办总金额")
    @TableField("agency_total_amount")
    private BigDecimal agencyTotalAmount;

    @ApiModelProperty(value = "评审总费用")
    @TableField(exist = false)
    private BigDecimal totalEvaluationFee;

    @ApiModelProperty(value = "订单状态")
    @TableField("order_status")
    private Integer orderStatus;

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

    @ApiModelProperty(value = "学历提升列表")
    @TableField(exist = false)
    private List<ClassThreeAssessorDO> listAssessors;

    @ApiModelProperty(value = "学历提升")
    @TableField(exist = false)
    private EducationPromotionDO educationPromotion;
}
