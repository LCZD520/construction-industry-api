package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_operation_log")
@ApiModel(value="OperationLogDO对象", description="操作日志表")
public class OperationLogSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户中文姓名")
    @TableField("user_chinese_name")
    private String userChineseName;

    @ApiModelProperty(value = "机构名称")
    @TableField("mechanism_name")
    private String mechanismName;

    @ApiModelProperty(value = "日志内容")
    @TableField("log_content")
    private String logContent;

    @ApiModelProperty(value = "操作起始日期")
    private String startDate;

    @ApiModelProperty(value = "操作结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;


}
