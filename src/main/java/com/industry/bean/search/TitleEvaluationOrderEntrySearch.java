package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 职称评审订单入账搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_title_evaluation_order_entry")
@ApiModel(value = "TitleEvaluationOrderEntryDO对象", description = "职称评审订单入账表")
public class TitleEvaluationOrderEntrySearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    @TableField("orderno")
    private String orderno;

    @ApiModelProperty(value = "客户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "申请状态")
    @TableField("application_status")
    private String status;

    @ApiModelProperty(value = "职称评审录入人id")
    @TableField("title_evaluation_creatorId")
    private Integer titleEvaluationCreatorId;

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


}
