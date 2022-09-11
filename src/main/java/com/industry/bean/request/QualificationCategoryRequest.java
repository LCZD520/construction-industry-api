package com.industry.bean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @author lc
 * @since 2022-07-01
 */
@Data
public class QualificationCategoryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "主键id不能为空", groups = Update.class)
    @Null(message = "主键id必须为空", groups = Insert.class)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "类别名称")
    private String categoryName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
