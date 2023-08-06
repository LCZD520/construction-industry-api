package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 资质代办搜索条件
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_qualification_agency")
public class QualificationAgencySearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "代办公司")
    private String agencyCompany;

    @ApiModelProperty(value = "代办资质")
    private String agencyQualification;

    @ApiModelProperty(value = "办理地区")
    private List<String> area;

    @ApiModelProperty(value = "资质代办状态")
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
