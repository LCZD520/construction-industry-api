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
 * 操作日志搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_login_log")
@ApiModel(value="LoginLogDO对象", description="登录日志表")
public class LoginLogSearch implements Serializable {

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

    @ApiModelProperty(value = "登录起始日期")
    private String startDate;

    @ApiModelProperty(value = "登录结束日期")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;


}
