package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_logistics")
@ApiModel(value="LogisticsDO对象", description="后勤管理日志表")
public class LogisticsSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "后勤项目")
    private Integer logisticsProjectType;

    @ApiModelProperty(value = "后勤申请状态")
    private Integer status;

    @ApiModelProperty(value = "申请人id")
    private Integer creatorId;

    @ApiModelProperty(value = "提交起始日期")
    private String startDate;

    @ApiModelProperty(value = "提交结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;


}
