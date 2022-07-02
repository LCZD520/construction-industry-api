package com.industry.enums;

import lombok.Getter;

/**
 * @author lc
 * @date 2022/2/22
 */

@Getter
public enum ResultCodeEnum {
    /* 成功状态码 */
    SUCCESS(true, "00000", "请求成功"),
    SUCCESS_INSERT(true, "00000", "添加成功"),
    SUCCESS_MODIFIED(true, "00000", "修改成功"),
    SUCCESS_DELETED(true, "00000", "删除成功"),
    FAIL_DELETED(true, "00000", "删除失败"),

    INSERT_FAILURE(false, "5000", "添加失败"),
    FAIL_MODIFIED(true, "5000", "修改失败"),
    SUCCESS_INSERT_EXIST(true, "00000", "地址已存在"),
    USER_FORBIDDEN(false, "403", "访问权限不足"),
    USER_METHOD_NOT_ALLOWED(false, "405", "HTTP请求方法不允许"),
    /* 参数错误 */
    PARAM_IS_INVALID(false, "1001", "参数无效"),
    PARAM_IS_BLANK(false, "1002", "参数为空"),
    PARAM_TYPE_BIND_ERROR(false, "1003", "参数类型错误"),
    PARAM_NOT_COMPLETE(false, "1004", "参数缺失"),
    //    成功（2000~2999）
    USER_LOGIN_SUCCESS(true, "200", "登录成功"),
    USER_LOGOUT_SUCCESS(true, "200", "退出成功"),
    USER_REGISTER_SUCCESS(true, "200", "注册成功"),

    /* 用户错误 4000-4999*/
    ILLEGAL_ARGUMENT(false, "4000", "非法请求"),
    USER_UNAUTHORIZED_OR_SESSION_EXPIRED(false, "4001", "登录会话已过期,请重新登录"),

    USER_RESOURCE_NOT_FOUND(false, "4004", "请求资源不存在"),
    USER_CAPTCHA_NOT_FOUND(false, "4005", "验证码不能为空"),
    USER_CAPTCHA_VALID_ERROR(false, "4006", "验证码错误"),
    USER_CAPTCHA_VALID_TIMEOUT(false, "4006", "验证码已过期"),
    USER_CAPTCHA_RENDER_ERROR(false, "4006", "验证码渲染错误"),
    USER_OTHER_EXCEPTION(false, "4020", "其他异常"),

    USER_ACCOUNT_DISABLED(false, "4031", "账号不可用"),
    USER_ACCOUNT_CREDENTIAL_ERROR(false, "4008", "账号或密码错误"),
    USER_ACCOUNT_REGISTER_ERROR(false, "4009", "注册失败"),
    USER_ACCOUNT_ALREADY_EXIST_ERROR(false, "4009", "用户已存在"),

    ERROR_NOT_EXIST(false, "4010", "查找记录不存在"),

    INTERNAL_AUTHENTICATION_SERVICE_EXCEPTION(false, "4021", "内部身份验证服务异常"),

    SYSTEM_ERROR(false, "10003", "系统异常，请稍后重试");

    private final Boolean status;
    private final String code;
    private final String message;

    ResultCodeEnum(Boolean status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
