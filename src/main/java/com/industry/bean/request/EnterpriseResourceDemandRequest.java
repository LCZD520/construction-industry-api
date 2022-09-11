package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/9
 */
@Data
public class EnterpriseResourceDemandRequest {
    @ApiModelProperty(value = "企业资源需求主键id")
    private Integer id;

    @NotNull(message = "企业资源主键id", groups = Insert.class)
    @ApiModelProperty(value = "企业资源主键id")
    private Integer enterpriseResourceId;

    @ApiModelProperty(value = "级别专业-初始")
    @TableField(value = "level_major_initial_conversion",typeHandler = JacksonTypeHandler.class)
    private String levelMajorInitialConversion;
}
