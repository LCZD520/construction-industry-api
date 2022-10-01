package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lc
 * @date 2022/9/17
 */
@Data
public class UnselectedAssessorRequest {

    @ApiModelProperty(value = "当前页")
    @NotNull(message = "当前页不能为空")
    private Long currentPage;

    @ApiModelProperty(value = "页数大小")
    private Long pageSize;

    @ApiModelProperty(value = "已选评审人员id")
    private List<Integer> listSelectedIds;
}
