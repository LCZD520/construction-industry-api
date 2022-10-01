package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
public class TitleEvaluationRequest {
    @ApiModelProperty(value = "职称评审主键id")
    @Null(message = "职称评审主键id必须为空", groups = Insert.class)
    @NotNull(message = "职称评审主键id必须不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "客户类型")
    @TableField("customer_type")
    private Integer customerType;

    @ApiModelProperty(value = "客户名称")
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
    private List<TalentCertificateRequest> listAssessors;
}
