package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @since 2022-07-01
 */
@Data
public class TalentOrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "企业id")
    private Integer enterpriseId;

    @ApiModelProperty(value = "订单编号")
    private String orderno;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业需求id集合")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String enterpriseDemandIds;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单需求")
    private String orderDemand;

    @ApiModelProperty(value = "已选人才")
    @Size(min = 1, message = "人才列表不能为空")
    private List<OrderSelectedTalentRequest> selectedTalents;

}
