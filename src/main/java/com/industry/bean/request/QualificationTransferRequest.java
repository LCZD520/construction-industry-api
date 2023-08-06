package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lc
 * @date 2022/7/15
 */
@Data
public class QualificationTransferRequest {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "转让意向客户")
    private String transferCustomers;

    @ApiModelProperty(value = "资质需求")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String qualificationRequirements;

    @ApiModelProperty(value = "所在地区")
    private String area;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
