package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lc
 * @date 2022/7/6
 */
@Data
public class LogisticsConfirmRequest {

    @NotNull(message = "更新主键id不能为空", groups = Update.class)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "请选择是否确认", groups = Update.class)
    @ApiModelProperty(value = "是否确认")
    private Boolean confirm;

    @ApiModelProperty(value = "后勤记录确认备注")
    private String confirmRemark;

    @ApiModelProperty(value = "后勤记录确认费用")
    private BigDecimal confirmCost;
}
