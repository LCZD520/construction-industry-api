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
@TableName("t_qualification_acquisition_transfer")
public class QualificationAcquisitionTransferSearch {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申请人")
    private Integer creatorId;

    @ApiModelProperty(value = "申请状态")
    private String status;

    @ApiModelProperty(value = "转让意向客户")
    private String transferIntendedCustomer;

    @ApiModelProperty(value = "收购意向客户")
    private String acquisitionIntendedCustomer;

    @ApiModelProperty(value = "申请起始日期")
    private String startDate;

    @ApiModelProperty(value = "申请结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

}
