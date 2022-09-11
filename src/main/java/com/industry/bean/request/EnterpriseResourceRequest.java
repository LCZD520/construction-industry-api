package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/13
 */
@Data
public class EnterpriseResourceRequest {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "资源是否共享")
    private Boolean shared;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业资质")
    private Integer enterpriseQualifications;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "联系地址")
    private String contactAddress;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;

    @ApiModelProperty(value = "QQ号码")
    private String qqNumber;

    @ApiModelProperty(value = "客户类型")
    private Integer customerType;

    @ApiModelProperty(value = "跟进情况")
    private String followUpSituation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "企业需求")
    private List<EnterpriseResourceDemandRequest> listEnterpriseDemands;
}
