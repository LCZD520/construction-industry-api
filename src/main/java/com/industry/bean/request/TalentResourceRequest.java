package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/12
 */
@Data
public class TalentResourceRequest {

    @ApiModelProperty(value = "主键id")
    @Null(message = "主键id必须为空", groups = Insert.class)
    @NotNull(message = "主键id必须不能为空", groups = Update.class)
    private Integer id;

    @NotNull(message = "资源是否共享不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "资源是否共享")
    private Boolean shared;

    @Length(min = 2, max = 10, groups = {Insert.class, Update.class})
    @NotBlank(message = "姓名不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "姓名")
    private String fullName;

    private Integer sex;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "社保")
    private String socialSecurity;

    @NotBlank(message = "电话号码不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    private String qqNumber;

    @ApiModelProperty(value = "学历")
    private Integer education;

    @ApiModelProperty(value = "职称")
    private Integer title;

    @ApiModelProperty(value = "三类人员")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @ApiModelProperty(value = "证书状态")
    private Integer certificateStatus;

    @ApiModelProperty(value = "客户类型")
    private Integer talentType;

    @ApiModelProperty(value = "人才价格")
    private Integer talentPrice;

    @ApiModelProperty(value = "人才价格数量")
    private Integer talentPriceNumber;

    @ApiModelProperty(value = "价格数量单位")
    private Integer numberUnit;


    @ApiModelProperty(value = "人才要求")
    private String talentRequirement;

    @ApiModelProperty(value = "跟进情况")
    private String followUpSituation;

    @ApiModelProperty(value = "备注")
    private String remark;

    private List<TalentResourceCertificateRequest> listCertificates;

}
