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
    SUCCESS_ROLE_DISABLE(true, "00000", "角色已禁用"),
    SUCCESS_ROLE_ENABLED(true, "00000", "角色已启用"),
    SUCCESS_INSERT(true, "00000", "添加成功"),
    SUCCESS_SAVE(true, "00000", "保存成功"),
    SUCCESS_MODIFIED(true, "00000", "修改成功"),
    SUCCESS_SUBMITED(true, "00000", "提交成功"),
    SUCCESS_CONFIRM(true, "00000", "订单确认成功"),
    SUCCESS_MERGE_ORDER(true, "00000", "订单合并成功"),
    FAIL_MERGE_ORDER(false, "00000", "订单合并失败"),
    SUCCESS_ORDER_CANCELED(true, "00000", "订单已取消"),
    FAIL_ORDER_CANCELED_EXIST_LATEST_STATUS(false, "00000", "取消失败,当前订单状态已变更,请刷新查看最新订单状态"),
    FAIL_ORDER_CANCELED(false, "00000", "取消失败"),
    SUCCESS_PLACE_ORDER(true, "00000", "下单成功"),
    SUCCESS_OPERATION(true, "00000", "操作成功"),
    SUCCESS_MODIFIED_PASSWORD(true, "00000", "修改密码成功，需重新登录"),


    FAIL_SAVE(false, "00000", "保存失败"),
    FAIL_OPERATION(false, "00000", "操作失败"),
    FAIL_PLACE_ORDER(false, "00000", "下单失败"),
    FAIL_PLACE_REPEAT_ASSESSOR_ORDER(false, "00000", "存在重复下单人员"),
    SUCCESS_NOT_EXIST_MODIFIED(false, "00000", "您要修改的记录不存在"),
    SUCCESS_DELETED(true, "00000", "删除成功"),
    SUCCESS_RECOVERIED(true, "00000", "数据已恢复"),
    FAIL_DELETED(false, "00000", "删除失败"),
    FAIL_RECOVERIED(false, "00000", "恢复失败"),
    FAIL_EXIST_SUB_MECHANISMS_DELETED(false, "00000", "你要删除的机构存在子机构，删除失败"),
    FAIL_NOT_EXIST_DELETED(false, "00000", "您要删除的记录不存在"),
    FAIL_NOT_EXIST_ORDER(false, "00000", "您要取消的订单不存在"),
    FAIL_NOT_EXIST_RECOVERY(false, "00000", "您要恢复的记录不存在"),
    FAIL_SUBMITED(true, "00000", "提交失败"),

    INSERT_FAILURE(false, "5000", "添加失败"),
    FAIL_CATEGORY_NAME_EXIST(false, "5000", "分类名称已重复"),
    ROLE_NOT_FOUND(false, "5000", "该角色不存在"),
    FAIL_MODIFIED(false, "5000", "修改失败"),
    SUCCESS_INSERT_EXIST(true, "00000", "地址已存在"),
    USER_FORBIDDEN(false, "403", "访问权限不足"),
    USER_METHOD_NOT_ALLOWED(false, "405", "HTTP请求方法不允许"),

    // 资质转让订单下单
    QUALIFICATION_TRANSFER_NOT_EXIST(false, "5000", "资质转让记录不存在"),
    QUALIFICATION_ACQUISITION_NOT_EXIST(false, "5000", "转让意向客户不存在"),
    QUALIFICATION_TRANSFER_ORDER_REPEAT(false, "5000", "资质转让订单已重复"),
    QUALIFICATION_TRANSFER_CUSTOMER_IS_EXIST(false, "5000", "该转让意向客户已被下单，请选择其他转让意向客户"),
    /* 参数错误 */
    PARAM_VALID_FAIL(false, "4000", "参数校验失败"),
    PARAM_ASSESSOR_FEE_VALID_FAIL(false, "4000", "评审费用填写未完整"),
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
    USER_ACCOUNT_ALREADY_EXIST_ERROR(false, "4010", "用户已存在"),
    USER_ACCOUNT_NO_FOUND_ERROR(false, "4011", "您要修改的账户不存在"),
    USER_MODIFIED_PASSWORD_SAME_ERROR(false, "4012", "修改新旧密码不能相同"),
    USER_MODIFIED_PASSWORD_ERROR(false, "4013", "修改密码失败，请重试"),
    USER_MODIFIED_OLD_PASSWORD_ERROR(false, "4013", "清输入正确的旧密码"),

    SUCCESS_APPLY_TRANSFER(true, "00000", "转账申请成功"),
    FAIL_APPLY_EXISTED_TALENT(false, "4000", "转账申请失败，存在已分配人才"),

    FAIL_UNSET_TRANSFER_AUDIT(false, "00000", "系统还未进行转账审核设置"),
    FAIL_UNSET_ENTRY_AUDIT(false, "00000", "系统还未进行入账审核设置"),
    FAIL_UNSET_ACHIEVEMENT_AUDIT(false, "00000", "系统还未进行业绩审核设置"),

    FAIL_NOT_FOUND_ANY_ROLE(false, "00000", "您目前不具备任何角色，请联系超级管理员设置角色"),
    FAIL_NOT_EXIST_RECORD(false, "00000", "审核失败,该入账记录不存在，请刷新后重试！"),
    FAIL_EXIST_LATEST_AUDIT_RECORD(false, "00000", "审核失败,存在最新审核记录！"),
    SUCCESS_AUDIT_RECORD(true, "00000", "审核成功！"),
    FAIL_AUDIT_RECORD(false, "00000", "审核失败！"),

    ERROR_NOT_EXIST(false, "4010", "查找记录不存在"),

    INTERNAL_AUTHENTICATION_SERVICE_EXCEPTION(false, "4021", "内部身份验证服务异常"),
    FAIL_EXIST_PERFORM_CONFIRM_RECORD(false, "00000", "该记录已存在最新的执行确认记录，请前往资质查看最新执行记录"),
    FAIL_ORDER_EXISTED_TALENT(false, "00000", "下单失败，存在已被下单的人才"),
    FAIL_ORDER_NOT_EXIST_TALENT(false, "00000", "下单失败，所选人员不存在"),
    FAIL_TALENT_ENTRY_RECORD_NO_PASS(false, "00000", "人才入账审核未通过"),
    FAIL_TALENT_TRANSFER_RECORD_NO_PASS(false, "00000", "人才转账审核未通过"),


    SYSTEM_ERROR(false, "500", "系统异常，请稍后重试"),
    SERVICE_BUSY(false, "503", "服务繁忙，请稍后重试");

    private final Boolean status;
    private final String code;
    private final String message;

    ResultCodeEnum(Boolean status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
