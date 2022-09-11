package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lc
 * @since 2022-07-01
 */
@Data
public class CertificateCategoryRequest implements Serializable {

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
