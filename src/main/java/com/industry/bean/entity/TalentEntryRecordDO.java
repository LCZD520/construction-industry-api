package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 人才入账记录表
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_entry_record")
@ApiModel(value = "TalentEntryRecordDO对象", description = "人才入账记录表")
public class TalentEntryRecordDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才入账id")
    @TableField("talent_entry_id")
    private Integer talentEntryId;

    @ApiModelProperty(value = "人才订单id")
    @TableField("talent_order_id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "申请状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "人才id")
    @TableField("talent_id")
    private Integer talentId;

    @ApiModelProperty(value = "人才姓名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "转入金额")
    @TableField("entry_amount")
    private Integer entryAmount;

    @ApiModelProperty(value = "转账用途")
    @TableField("funds_purpose")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "其他补贴费用")
    @TableField("other_fee")
    private Integer otherFee;

    @ApiModelProperty(value = "合同余额")
    @TableField("contract_balance")
    private Integer contractBalance;

    @ApiModelProperty(value = "企业出价")
    @TableField("enterprise_offer")
    private Integer enterpriseOffer;

    @ApiModelProperty(value = "企业出价数量")
    @TableField("enterprise_offer_number")
    private Integer enterpriseOfferNumber;

    @ApiModelProperty(value = "企业出价单位")
    @TableField("enterprise_offer_unit")
    private Integer enterpriseOfferUnit;

    @ApiModelProperty(value = "人才价格")
    @TableField("talent_price")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    @TableField("talent_price_number")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "人才价格单位")
    @TableField("number_unit")
    private Integer numberUnit;

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

    @ApiModelProperty(value = "审批意见")
    @TableField("approval_opinion")
    private String approvalOpinion;

    @ApiModelProperty(value = "人才入账")
    @TableField(exist = false)
    private TalentEntryDO talentEntry;

    @ApiModelProperty(value = "人才订单")
    @TableField(exist = false)
    private TalentOrderDO talentOrder;

    @ApiModelProperty(value = "人才入账记录")
    @TableField(exist = false)
    private List<TalentEntryRecordDO> listTalentEntryRecords;

}
