package com.industry.bean.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
public class TalentRequest {
    @ApiModelProperty(value = "人才主键id")
    @Null(message = "主键id必须为空", groups = Insert.class)
    @NotNull(message = "主键id必须不能为空", groups = Update.class)
    private Integer id;

    @Length(min = 2, max = 10, groups = {Insert.class, Update.class})
    @NotBlank(message = "姓名不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "姓名")
    private String fullName;

    @NotNull(message = "性别不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @NotBlank(message = "地区不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "地区")
    private String area;

    @NotBlank(message = "社保不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "社保")
    private String socialSecurity;

    @NotBlank(message = "电话号码不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    private String qqNumber;

    @NotNull(message = "学历不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "学历")
    private Integer education;

    @ApiModelProperty(value = "职称")
    private Integer title;

    @NotNull(message = "三类人员不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "三类人员")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "发证时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date issueCertTime;

    @ApiModelProperty(value = "继续教育时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date continuingEducationDate;

    @NotNull(message = "招标出场不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @NotNull(message = "证书状态不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "证书状态")
    private Integer certificateStatus;

    @ApiModelProperty(value = "人才状态")
    private Integer talentStatus;

    @NotNull(message = "人才价格不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "人才价格")
    private Integer talentPrice;

    @NotNull(message = "请选择", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "人才价格数量")
    private Integer talentPriceNumber;

    @NotNull(message = "请选择", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "价格数量单位")
    private Integer numberUnit;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Size(min = 1, message = "证书不能为空", groups = {Insert.class, Update.class})
    private List<TalentCertificateRequest> listCertificates;
}
