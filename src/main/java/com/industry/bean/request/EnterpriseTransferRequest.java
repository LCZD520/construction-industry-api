package com.industry.bean.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class EnterpriseTransferRequest {

    @NotNull(message = "企业id不能为空")
    private Integer enterpriseId;

    @NotNull(message = "账户名不能为空")
    private String accountName;

    @NotNull(message = "银行卡号不能为空")
    private String bankCardNo;

    @NotNull(message = "银行名称不能为空")
    private String bankName;

    @NotBlank(message = "转账用途不能为空")
    private String fundsPurpose;

    private String openBank;

    @Size(min = 1,message = "orderSelectedTalentIds最少长度为1")
    private List<Integer> orderSelectedTalentIds;

    private String remark;

    @NotNull(message = "转账金额不能为空")
    private Integer transferAmount;

}
