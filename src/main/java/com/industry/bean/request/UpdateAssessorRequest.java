package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lc
 * @date 2022/9/17
 */
@Data
public class UpdateAssessorRequest {

    @ApiModelProperty(value = "评审人员id")
    @NotNull(message = "评审人员id不能为空")
    private Integer id;

    @ApiModelProperty(value = "代办费用")
    @NotNull(message = "代办费用不能为空")
    @DecimalMin(value = "0.01", message = "代办费用必须大于0")
    private BigDecimal agencyAmount;

    @ApiModelProperty(value = "评审费用")
    @NotNull(message = "评审费用不能为空")
    @DecimalMin(value = "0.01", message = "评审费用必须大于0")
    private BigDecimal evaluationFee;
}
