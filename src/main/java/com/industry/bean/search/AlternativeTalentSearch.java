package com.industry.bean.search;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 备选人才搜索
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlternativeTalentSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "招标出场")
    private Integer tenderExit;

    @ApiModelProperty(value = "级别-专业-初转")
    private List<LevelMajorInitialConversion> levelMajorInitialConversion;

    @ApiModelProperty(value = "每页大小")
    private Long pageSize;

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

}

@Data
class LevelMajorInitialConversion {

    @ApiModelProperty(value = "级别专业")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String levelMajor;

    @ApiModelProperty(value = "初始转注")
    private Integer initialConversion;
}
