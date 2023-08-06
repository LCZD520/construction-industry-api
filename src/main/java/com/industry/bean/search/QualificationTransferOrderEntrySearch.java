package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 资质转让入账搜索条件
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer_order_entry")
public class QualificationTransferOrderEntrySearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String orderno;

    @ApiModelProperty(value = "收购意向客户")
    private String transferCustomers;

    @ApiModelProperty(value = "申请状态")
    private String applicationStatus;

    @ApiModelProperty(value = "申请人id")
    private Integer creatorId;

    @ApiModelProperty(value = "申请起始日期")
    private String startDate;

    @ApiModelProperty(value = "申请结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

}
