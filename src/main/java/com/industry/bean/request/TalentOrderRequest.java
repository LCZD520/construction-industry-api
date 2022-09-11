package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.industry.bean.entity.OrderSelectedTalentDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    private List<OrderSelectedTalentRequest> selectedTalents;

}
