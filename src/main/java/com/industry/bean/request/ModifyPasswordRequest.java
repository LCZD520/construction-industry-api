package com.industry.bean.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lc
 * @date 2022/7/29
 */
@Data
public class ModifyPasswordRequest {

    @NotNull(message = "请输入用户id")
    private Integer id;

    @NotBlank(message = "请输入旧密码")
    private String oldPassword;

    @NotBlank(message = "请输入旧密码")
    private String newPassword;
}
