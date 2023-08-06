package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class RemarksRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;

    @NotBlank(message = "请输入备注")
    @ApiModelProperty(value = "备注")
    private String remark;
}
