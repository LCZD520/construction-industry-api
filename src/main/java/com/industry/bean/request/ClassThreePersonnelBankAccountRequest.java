package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author lc
 * @date 2022/7/8
 */
@Data
public class ClassThreePersonnelBankAccountRequest {

    @NotNull(message = "主键id不能为空", groups = Update.class)
    @Null(message = "主键id必须为空", groups = Insert.class)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "三类人员订单id不能为空", groups = Insert.class)
    @ApiModelProperty(value = "三类人员订单id")
    private Integer classThreePersonnelOrderId;

    @NotBlank(message = "银行不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "银行")
    private Integer bankName;

    @NotBlank(message = "开户行不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "开户行")
    private String openBank;

    @NotBlank(message = "户名不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "户名")
    private String accountName;

    @NotBlank(message = "银行卡号不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
}
