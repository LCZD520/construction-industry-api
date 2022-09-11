package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 人才入账表
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent_entry")
@ApiModel(value = "TalentEntryDO对象", description = "人才入账表")
public class TalentEntryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司账户id")
    @TableField("enterprise_account_id")
    private Integer enterpriseAccountId;

    @ApiModelProperty(value = "人才订单id")
    @TableField("talent_order_id")
    private Integer talentOrderId;

    @ApiModelProperty(value = "转账方信息")
    @TableField("transferor_info")
    private String transferorInfo;

    @ApiModelProperty(value = "转账方式")
    @TableField("transfer_way")
    private Integer transferWay;

    @ApiModelProperty(value = "转账日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("transfer_date")
    private Date transferDate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "转入记录")
    @TableField(exist = false)
    private List<TalentEntryRecordDO> listEntrys;

    @ApiModelProperty(value = "人才订单")
    @TableField(exist = false)
    private TalentOrderDO talentOrder;

}
