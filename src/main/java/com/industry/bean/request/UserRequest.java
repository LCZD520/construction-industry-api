package com.industry.bean.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lc
 * @date 2022/9/11
 */
@Data
public class UserRequest implements Serializable {
    private static final long serialVersionUID = -371828410719277231L;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {Update.class})
    @Null(message = "用户id必须为空", groups = {Insert.class})
    private Integer userId;

    /**
     * 机构id
     */
    @NotNull(message = "机构id不能为空", groups = {Insert.class, Update.class})
    private Integer mechanismId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {Insert.class, Update.class})
    private String fullName;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Insert.class, Update.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Insert.class, Update.class})
    private String password;

    /**
     * 是否在职
     */
    @NotNull(message = "是否在职不能为空", groups = {Insert.class, Update.class})
    private Boolean onJob;

    /**
     * 角色id集合
     */
    @Size(min = 1, message = "请设置角色", groups = {Insert.class, Update.class})
    private List<Integer> listRoleIds;

    /**
     * 联系方式
     */
    private String telephoneNumber;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

}
