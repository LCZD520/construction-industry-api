package com.industry.bean.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class ApprovalOpinionRequest {

    @NotNull(message = "请选择是否同意审核")
    private Boolean adopt;

    private String approvalOpinion;
}
