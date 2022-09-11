package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.entity.TalentEntryRecordDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 人才入账表
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Data
public class TalentEntryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "公司账户id")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "人才订单id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "转账方信息")
    private String transferorInfo;

    @ApiModelProperty(value = "转账方式")
    private Integer transferWay;

    @ApiModelProperty(value = "转账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("transfer_date")
    private Date transferDate;

    @ApiModelProperty(value = "转入记录")
    @TableField(exist = false)
    private List<TalentEntryRecordRequest> listEntrys;


}
