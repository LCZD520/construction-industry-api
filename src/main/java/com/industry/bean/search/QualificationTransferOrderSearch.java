package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lc
 * @date 2023/3/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_order")
public class QualificationTransferOrderSearch {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String orderno;

    @ApiModelProperty(value = "转让意向客户")
    private String transferIntendedCustomer;

    @ApiModelProperty(value = "收购意向客户")
    private String acquisitionIntendedCustomer;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "资质转让录入人")
    private Integer qualificationTransferCreatorId;

    @ApiModelProperty(value = "资质收购录入人")
    private Integer qualificationAcquisitionCreatorId;

    @ApiModelProperty(value = "订单起始日期")
    private String startDate;

    @ApiModelProperty(value = "订单结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

}
