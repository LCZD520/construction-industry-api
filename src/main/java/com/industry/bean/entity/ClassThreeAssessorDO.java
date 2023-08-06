package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 三类评审人员信息表
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_class_three_assessor")
@ApiModel(value = "ClassThreeAssessorDO对象", description = "三类评审人员信息表")
public class ClassThreeAssessorDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "三类人员id")
    @TableField("class_three_person_id")
    private Integer classThreePersonId;

    @ApiModelProperty(value = "三类人员订单id")
    @TableField("class_three_person_order_id")
    private Integer classThreePersonOrderId;

    @ApiModelProperty(value = "姓名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证")
    @TableField("identity_card")
    private String identityCard;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "安全证书")
    @TableField("security_certificate")
    private String securityCertificate;

    @ApiModelProperty(value = "三类类别")
    @TableField("three_category")
    private String threeCategory;

    @ApiModelProperty(value = "事项性质")
    @TableField("item_nature")
    private String itemNature;

    @ApiModelProperty(value = "闽政通账号")
    @TableField("min_zheng_tong_account")
    private String minZhengTongAccount;

    @ApiModelProperty(value = "闽政通密码")
    @TableField("min_zheng_tong_password")
    private String minZhengTongPassword;

    @ApiModelProperty(value = "评审费用")
    @TableField("evaluation_fee")
    private BigDecimal evaluationFee;

    @ApiModelProperty(value = "代办金额")
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

    @ApiModelProperty(value = "评审状态")
    @TableField("evaluation_status")
    private Integer evaluationStatus;

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

    @ApiModelProperty(value = "三类人员图片")
    @TableField(exist = false)
    private List<PictureTempDO> listImages;
}
