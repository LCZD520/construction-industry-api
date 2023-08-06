package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 人才入账登记表
 * </p>
 *
 * @author lc
 * @since 2023-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_entry_registration")
@ApiModel(value = "EntryRegistrationDO对象", description = "人才入账登记表")
public class EntryRegistrationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(message = "主键id必须为空", groups = Insert.class)
    private Integer id;

    @ApiModelProperty(value = "入账类型")
    @TableField("entry_type")
    @NotNull(message = "入账类型不能为空")
    private Integer entryType;

    @ApiModelProperty(value = "客户名称")
    @TableField("customer_name")
    @NotBlank(message = "客户名称不能为空")
    private String customerName;

    @ApiModelProperty(value = "转账用途")
    @TableField("funds_purpose")
    @NotNull(message = "转账用途不能为空")
    private Integer fundsPurpose;

    @ApiModelProperty(value = "转入金额")
    @TableField("entry_amount")
    @NotNull(message = "转入金额不能为空")
    private BigDecimal entryAmount;

    @ApiModelProperty(value = "转账方信息")
    @TableField("transferor_info")
    @NotBlank(message = "转账方信息不能为空")
    private String transferorInfo;

    @ApiModelProperty(value = "转账方式")
    @TableField("transfer_way")
    @NotNull(message = "转账方式不能为空")
    private Integer transferWay;

    @ApiModelProperty(value = "公司账户id")
    @TableField("enterprise_account_id")
    @NotNull(message = "公司账户不能为空")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "转账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("transfer_date")
    private Date transferDate;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


}
