package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 录入量统计搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InputQuantityStatisticsSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "起始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    private String endDate;

}
