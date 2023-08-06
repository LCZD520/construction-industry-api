package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 职称评审/三类人员/学历提升下单请求体
 * </p>
 *
 * @author lc
 * @date 2022/9/17
 */
@Data
public class TitleEvaluationPlaceOrderRequest {

    @ApiModelProperty(value = "职称评审/三类人员/学历提升id")
    @NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "已选评审人员id")
    @Size(min = 1, message = "请选择评审人员")
    private List<Integer> listSelectedIds;
}
