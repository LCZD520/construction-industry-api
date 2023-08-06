package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职称评审转账银行账户表
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_title_evaluation_bank_account")
@ApiModel(value="TitleEvaluationBankAccountDO对象", description="职称评审转账银行账户表")
public class TitleEvaluationBankAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职称评审订单id")
    @NotNull(message = "职称评审订单id不能为空",groups = Insert.class)
    @TableField("title_evaluation_order_id")
    private Integer titleEvaluationOrderId;

    @ApiModelProperty(value = "银行")
    @TableField("bank_name")
    private Integer bankName;

    @ApiModelProperty(value = "开户行")
    @TableField("open_bank")
    private String openBank;

    @ApiModelProperty(value = "户名")
    @TableField("account_name")
    private String accountName;

    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;


}
