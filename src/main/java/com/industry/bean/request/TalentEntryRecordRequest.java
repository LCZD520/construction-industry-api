package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/27
 */
@Data
public class TalentEntryRecordRequest {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "人才入账id")
    private Integer talentEntryId;

    @ApiModelProperty(value = "人才订单id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "申请状态")
    private Integer status;

    @ApiModelProperty(value = "人才id")
    private Integer talentId;

    @ApiModelProperty(value = "人才姓名")
    private String fullName;

    @ApiModelProperty(value = "转入金额")
    private Integer entryAmount;

    @ApiModelProperty(value = "转账用途")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "其他补贴费用")
    private Integer otherFee;

    @ApiModelProperty(value = "合同余额")
    private Integer contractBalance;

    @ApiModelProperty(value = "企业出价")
    private Integer enterpriseOffer;

    @ApiModelProperty(value = "企业出价数量")
    private Integer enterpriseOfferNumber;

    @ApiModelProperty(value = "企业出价单位")
    private Integer enterpriseOfferUnit;

    @ApiModelProperty(value = "人才价格")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "人才价格单位")
    private Integer numberUnit;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

    @ApiModelProperty(value = "转入记录")
    @TableField(exist = false)
    private List<TalentEntryRecordRequest> listEntrys;
}
