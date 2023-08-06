package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 后勤管理搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_order")
@ApiModel(value="TalentOrderDO对象", description="人才订单表")
public class TalentOrderSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String orderno;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "人才名称")
    private String fullName;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "企业录入人id")
    private Integer enterpriseCreatorId;

    @ApiModelProperty(value = "订单创建起始日期")
    private String startDate;

    @ApiModelProperty(value = "订单创建结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;


}
