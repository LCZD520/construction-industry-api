package com.industry.bean.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 备选人才搜索2
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlternativeTalentSearch2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "级别专业")
    private String levelMajor;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

    @ApiModelProperty(value = "已选人才id列表")
    List<Integer> listSelectedIds;
}

