package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/9
 */
@Data
public class EnterpriseRequest {

    @Null(message = "企业主键id必须为空", groups = Insert.class)
    @ApiModelProperty(value = "企业主键id")
    private Integer id;

    @NotBlank(message = "企业名称不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @NotNull(message = "企业资质不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业资质")
    private Integer enterpriseQualifications;

    @NotBlank(message = "地区不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "地区")
    @TableField("area")
    private String area;

    @NotBlank(message = "联系地址不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "联系地址")
    private String contactAddress;

    @NotBlank(message = "联系人不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "联系人")
    private String contacts;

    @NotNull(message = "性别不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @NotBlank(message = "电话号码不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    private String qqNumber;

    @ApiModelProperty(value = "企业状态")
    private Integer enterpriseStatus;

    @Size(min = 1, message = "企业需求不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "企业需求")
    private List<EnterpriseDemandRequest> listEnterpriseDemands;

    @ApiModelProperty(value = "备注")
    private String remark;

}
