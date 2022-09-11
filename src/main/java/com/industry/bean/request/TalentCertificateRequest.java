package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人才证书表
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TalentCertificate对象", description="人才证书表")
public class TalentCertificateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人才证书主键id")
    private Integer id;

    @ApiModelProperty(value = "人才主键id")
    private Integer talentId;

    @ApiModelProperty(value = "级别-专业")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String levelMajor;

    @ApiModelProperty(value = "初始转注")
    private Integer initialConversion;

    @ApiModelProperty(value = "发证时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date issueCertTime;

    @ApiModelProperty(value = "继续教育时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date continuingEducationDate;


}
