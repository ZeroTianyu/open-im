package com.openim.user.enums;

public enum ResultMessage implements ResultCode {
    SUCCESS(200, "操作成功", true),
    UNKNOW_ERROR(5055, "服务异常,请稍后再试!", false),
    FEIGN_FAILURE(5054, "服务调用异常,请稍后再试!", false),
    VALIDATE_ERROR(5001, "参数验证异常", false),
    NOT_FOUND(5002, "资源不存在", false),
    INVOKE_FAILURE(5003, "访问失败", false),
    ACCESS_DENIED_ERROR(5005, "访问受限", false),
    PARAMETER_ERROR(5006, "请求参数有误", false),
    VAILD_USER_ERROR(5223, "无效的用户信息", false),
    VAILD_USER_LOGIN_ERROR(5223, "用户未登录", false);

    private final int code;
    private final String message;
    private final Boolean success;

    private ResultMessage(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public Boolean getSuccess() {
        return success;
    }
}
