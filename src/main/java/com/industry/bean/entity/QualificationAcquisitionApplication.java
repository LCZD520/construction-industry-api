package com.industry.bean.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资质收购申请表
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_acquisition_application")
@ApiModel(value="QualificationAcquisitionApplication对象", description="资质收购申请表")
public class QualificationAcquisitionApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资质收购转账申请主键id")
    @TableId(value = "qualification_acquisition_application_id", type = IdType.AUTO)
    private Integer qualificationAcquisitionApplicationId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "申请备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "审批状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "申请转账金额")
    @TableField("applied_transfer_amount")
    private BigDecimal appliedTransferAmount;

    @ApiModelProperty(value = "款项用途")
    @TableField("purpose_funds")
    private Integer purposeFunds;

    @ApiModelProperty(value = "转账账户")
    @TableField("transfer_account")
    private String transferAccount;

}
