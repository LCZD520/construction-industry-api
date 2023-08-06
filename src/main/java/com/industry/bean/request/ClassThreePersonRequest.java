package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 三类人员表
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ClassThreePersonDO对象", description = "三类人员表")
public class ClassThreePersonRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "三类人员主键id")
    @Null(message = "三类人员主键id必须为空", groups = Insert.class)
    @NotNull(message = "三类人员主键id必须不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "客户类型")
    @NotNull(message = "客户类型不能为空", groups = Insert.class)
    @NotNull(message = "客户类型不能为空", groups = Update.class)
    @TableField("customer_type")
    private Integer customerType;

    @ApiModelProperty(value = "客户名称")
    @NotNull(message = "客户名称不能为空", groups = Insert.class)
    @NotNull(message = "客户名称不能为空", groups = Update.class)
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "负责人")
    @TableField("principal")
    private String principal;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @Size(min = 1, message = "评审人员信息不能为空", groups = {Insert.class, Update.class})
    private List<ClassThreeAssessorRequest> listAssessors;
}
