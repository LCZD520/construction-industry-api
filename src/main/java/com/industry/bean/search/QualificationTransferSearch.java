package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 资质转让搜索条件
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_transfer")
public class QualificationTransferSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收购意向客户")
    private String transferCustomers;

    @ApiModelProperty(value = "资质需求")
    private String qualificationRequirements;

    @ApiModelProperty(value = "地区")
    private List<String> area;

    @ApiModelProperty(value = "资质转让状态")
    private Integer status;

    @ApiModelProperty(value = "创建人id")
    private Integer creatorId;

    @ApiModelProperty(value = "录入起始日期")
    private String startDate;

    @ApiModelProperty(value = "录入结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

    @ApiModelProperty(value = "是否被删除")
    private Boolean deleted;

}
