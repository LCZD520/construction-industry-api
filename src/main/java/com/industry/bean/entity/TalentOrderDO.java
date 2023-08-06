package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 人才订单表
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_order")
@ApiModel(value = "TalentOrderDO对象", description = "人才订单表")
public class TalentOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业id")
    @TableField("enterprise_id")
    private Integer enterpriseId;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "企业名称")
    @TableField("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "企业需求id集合")
    @TableField("enterprise_demand_ids")
    private String enterpriseDemandIds;

    @ApiModelProperty(value = "订单状态")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单需求")
    @TableField("order_demand")
    private String orderDemand;


    @ApiModelProperty(value = "订单来源")
    @TableField("source")
    private String source;

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

    @ApiModelProperty(value = "已选人才")
    @TableField(exist = false)
    @Size(min = 1, message = "请选择人才")
    private List<OrderSelectedTalentDO> selectedTalents;

    @ApiModelProperty(value = "企业需求")
    @TableField(exist = false)
    private List<EnterpriseDemandDO> enterpriseDemands;

    @ApiModelProperty(value = "企业录入人id")
    @TableField(exist = false)
    private Integer enterpriseCreatorId;

    @ApiModelProperty(value = "资质代办录入人id")
    @TableField(exist = false)
    private Integer qualificationAgencyCreatorId;


}
