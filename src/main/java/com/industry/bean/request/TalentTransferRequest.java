package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lc
 * @date 2022/7/9
 */
@Data
public class TalentTransferRequest {
    @NotNull(message = "人才主键id不能为空")
    @ApiModelProperty(value = "人才主键id")
    private Integer talentId;

    @NotBlank(message = "银行不能为空")
    @ApiModelProperty(value = "银行")
    private String bankName;

    @NotBlank(message = "开户行不能为空")
    @ApiModelProperty(value = "开户行")
    private String openBank;

    @NotBlank(message = "户名不能为空")
    @ApiModelProperty(value = "户名")
    private String accountName;

    @NotBlank(message = "银行卡号不能为空")
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;

    @NotNull(message = "申请转账金额不能为空")
    @ApiModelProperty(value = "申请转账金额")
    private BigDecimal transferAmount;

    @NotNull(message = "款项用途不能为空")
    @ApiModelProperty(value = "款项用途")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "备注")
    private String remark;
}
