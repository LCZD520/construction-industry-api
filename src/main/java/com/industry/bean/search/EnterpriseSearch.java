package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 企业搜索条件
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_enterprise")
public class EnterpriseSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "地区")
    private List<String> area;

    @ApiModelProperty(value = "级别专业")
    private String levelMajor;

    @ApiModelProperty(value = "初始转注")
    private Integer initialConversion;

    @ApiModelProperty(value = "企业状态")
    private Integer enterpriseStatus;

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
