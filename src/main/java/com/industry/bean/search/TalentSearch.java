package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.entity.TalentCertificateDO;
import com.industry.bean.entity.TalentCertificatesDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 人才搜索条件
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_talent")
public class TalentSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "地区")
    private List<String> area;

    @ApiModelProperty(value = "级别专业")
    private String levelMajor;

    @ApiModelProperty(value = "社保")
    private List<String> socialSecurity;

    @ApiModelProperty(value = "职称")
    private Integer title;

    @ApiModelProperty(value = "三类人员")
    private Integer classThreePersonnel;

    @ApiModelProperty(value = "初始转注")
    private Integer initialConversion;

    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @ApiModelProperty(value = "人才状态")
    private Integer talentStatus;

    @ApiModelProperty(value = "创建人id")
    private Integer creatorId;

    @ApiModelProperty(value = "录入起始日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String startDate;

    @ApiModelProperty(value = "录入结束日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endDate;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

    @ApiModelProperty(value = "是否被删除")
    private Boolean deleted;

}
