package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.industry.bean.entity.PictureTempDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
public class ClassThreeAssessorRequest {
    @ApiModelProperty(value = "三类人员信息id")
    @Null(message = "三类人员信息id必须为空", groups = Insert.class)
    @NotNull(message = "三类人员信息id不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "三类人员id")
    @NotNull(message = "三类人员id不能为空", groups = {Insert.class, Update.class})
    @TableField("class_three_person_id")
    private Integer classThreePersonId;

    @ApiModelProperty(value = "三类人员订单id")
    @TableField("class_three_person_order_id")
    private Integer classThreePersonOrderId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空", groups = {Insert.class, Update.class})
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空", groups = {Insert.class, Update.class})
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证")
    @NotBlank(message = "身份证不能为空", groups = {Insert.class, Update.class})
    @TableField("identity_card")
    private String identityCard;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "安全证书")
    @NotBlank(message = "安全证书不能为空", groups = {Insert.class, Update.class})
    @TableField("security_certificate")
    private String securityCertificate;

    @ApiModelProperty(value = "三类类别")
    @NotBlank(message = "三类类别不能为空", groups = {Insert.class, Update.class})
    @TableField("three_category")
    private String threeCategory;

    @ApiModelProperty(value = "事项性质")
    @NotBlank(message = "事项性质不能为空", groups = {Insert.class, Update.class})
    @TableField("item_nature")
    private String itemNature;

    @ApiModelProperty(value = "闽政通账号")
    @NotBlank(message = "闽政通账号不能为空", groups = {Insert.class, Update.class})
    @TableField("min_zheng_tong_account")
    private String minZhengTongAccount;

    @ApiModelProperty(value = "闽政通密码")
    @NotBlank(message = "闽政通密码不能为空", groups = {Insert.class, Update.class})
    @TableField("min_zheng_tong_password")
    private String minZhengTongPassword;

    @ApiModelProperty(value = "评审费用")
    @TableField("evaluation_fee")
    private BigDecimal evaluationFee;

    @ApiModelProperty(value = "代办金额")
    @NotNull(message = "代办金额不能为空", groups = {Insert.class, Update.class})
    @TableField("agency_amount")
    private BigDecimal agencyAmount;

    private List<PictureTempDO> listImages;
}
