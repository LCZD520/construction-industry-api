package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @author lc
 * @date 2022/7/6
 */
@Data
public class LogisticsRequest {
    @Null(message = "主键id必须为空",groups = Insert.class)
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "人才主键id")
    private Integer talentId;

    @ApiModelProperty(value = "企业主键id")
    private Integer enterpriseId;

    @ApiModelProperty(value = "后勤记录添加备注")
    private String addRemark;

    @ApiModelProperty(value = "后勤项目")
    private Integer logisticsProjectType;

    @ApiModelProperty(value = "邮寄详细物品")
    private String detailItem;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;

    @ApiModelProperty(value = "收件人")
    @TableField("recipient")
    private String recipient;

    @Range(min = 1, max = 2, groups = {Insert.class, Update.class})
    @NotNull(message = "请指定后勤记录类型（1-人才，2-企业）", groups = Insert.class)
    @ApiModelProperty(value = "后勤记录类型（1-人才，2-企业）")
    private Integer logisticsType;

    @Digits(integer = 2, fraction = 0, message = "申请状态必须为2", groups = Update.class)
    @Digits(integer = 1, fraction = 0, message = "申请状态必须为2", groups = Insert.class)
    @ApiModelProperty(value = "申请状态")
    private Integer status;

}
