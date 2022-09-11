package com.industry.bean.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lc
 * @date 2022/7/6
 */
@Data
public class TalentVisitRequest {

    @NotNull(message = "主键id不能为空", groups = Update.class)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "人才主键id不能为空", groups = Insert.class)
    @ApiModelProperty(value = "人才主键id")
    private Integer talentId;

    @NotBlank(message = "回访内容不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "回访内容")
    private String visitContent;

    @NotNull(message = "请选择是否需要再次回访", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "是否需要再次回访")
    private Boolean needVisitAgain;

    @ApiModelProperty(value = "下次回访日期")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date visitAgainTime;
}
