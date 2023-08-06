package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/17
 */
@Data
public class QualificationAgencyEntryRequest {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(message = "主键id必须为空", groups = Insert.class)
    private Integer id;

    @ApiModelProperty(value = "代办公司id")
    @NotNull(message = "代办公司id不能为空")
    private Integer agencyCompanyId;

    @ApiModelProperty(value = "代办公司")
    @NotBlank(message = "代办公司不能为空")
    private String agencyCompany;

    @ApiModelProperty(value = "款项用途")
    @NotNull(message = "款项用途不能为空")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "转入金额")
    @NotNull(message = "转入金额不能为空")
    private BigDecimal entryAmount;

    @ApiModelProperty(value = "转账方信息")
    @TableField("transferor_info")
    private String transferorInfo;

    @ApiModelProperty(value = "公司账户id")
    @NotNull(message = "公司账户id不能为空")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "银行名称")
    @NotNull(message = "银行名称不能为空")
    private Integer bankName;

    @ApiModelProperty(value = "银行卡号")
    @NotBlank(message = "银行卡号不能为空")
    private String bankCardNo;

    @ApiModelProperty(value = "户名")
    @NotBlank(message = "户名不能为空")
    private String accountName;

    @ApiModelProperty(value = "开户行")
    @NotBlank(message = "开户行不能为空")
    private String openingBank;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty(value = "转账方式")
    @NotNull(message = "转账方式不能为空")
    private Integer transferWay;

    @ApiModelProperty(value = "到账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("receipt_date")
    @NotNull(message = "到账日期不能为空")
    private Date receiptDate;

    @ApiModelProperty(value = "备注")
    private String remark;
}
