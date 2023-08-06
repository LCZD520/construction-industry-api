package com.industry.bean.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 录入量统计搜索条件
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InputQuantityStatisticsDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户姓名")
    private String fullName;

    @ApiModelProperty(value = "所属分公司/部门/分组")
    private String mechanismName;

    @ApiModelProperty(value = "角色id集合")
    private List<Integer> listRoleIds;

    @ApiModelProperty(value = "人才录入数")
    private Integer totalTalent;

    @ApiModelProperty(value = "企业录入数")
    private Integer totalEnterprise;

    @ApiModelProperty(value = "资质收购录入数")
    private Long totalQualificationAcquisition;

    @ApiModelProperty(value = "资质转让录入数")
    private Long totalQualificationTransfer;

    @ApiModelProperty(value = "资质代办录入数")
    private Long totalQualificationAgency;

    @ApiModelProperty(value = "职称评审录入数")
    private Long totalTitleEvaluation;

    @ApiModelProperty(value = "三类人员录入数")
    private Long totalClassThreePerson;

    @ApiModelProperty(value = "学历提升录入数")
    private Long totalEducationPromotion;

}
