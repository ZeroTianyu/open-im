package com.openim.user.enums;


/**
 * 返回状态枚举类
 *
 * @author guoty
 **/
public enum ResponseCode implements ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功", true),
    /**
     * 服务异常
     */
    UNKNOW_ERROR(5055, "服务异常,请稍后再试!", false),
    /**
     * 服务调用异常
     */
    FEIGN_FAILURE(5054, "服务调用异常,请稍后再试!", false),
    /**
     * 参数验证异常
     */
    VALIDATE_ERROR(5001, "参数验证异常", false)
    /**
     * 资源不存在
     */
    ,
    NOT_FOUND(5002, "资源不存在", false),
    /**
     * 访问失败
     */
    INVOKE_FAILURE(5003, "访问失败", false),
    /**
     * 访问受限
     */
    ACCESS_DENIED_ERROR(5005, "访问受限", false),
    /**
     * 请求参数有误
     */
    PARAMETER_ERROR(5006, "请求参数有误", false),
    /**
     * 无效的用户信息
     */
    VAILD_USER_ERROR(5223, "无效的用户信息", false),
    /**
     * 用户未登录
     */
    VAILD_USER_LOGIN_ERROR(5223, "用户未登录", false),
    /**
     * 商品状态异常
     */
    STATUS_ERROR(5224, "商品状态异常", false),
    /**
     * 订单错误
     */
    ORDER_ERROR(5225, "订单错误", false),

    /**
     * 库存相关异常
     */
    INVENTORY_MAX_ERROR(20001, "库存不足", false),
    /**
     * 小于最小限制
     */
    INVENTORY_MIN_ERROR(20002, "小于最小限制", false),
    /**
     * 商品未找到
     */
    INVENTORY_NOT_FOUNT_ERROR(20003, "商品未找到", false),
    /**
     * 商品已下架
     */
    INVENTORY_NOT_PUT_AWAY(20004, "商品已下架", false),
    /**
     * 商品已锁定
     */
    INVENTORY_LOCKED(20005, "商品已锁定", false),
    /**
     * 商品版本验证错误
     */
    INVENTORY_VERSION_ERROR(20006, "商品版本信息错误", false),
    /**
     * 资源分布式锁锁定
     */
    INVENTORY_IS_BUSY(20007, "资源忙", false),

    BAD_SQL_ERROR(30001, "数据库字段不匹配", false)

    //
    ;


    private final int code;
    private final String message;
    private final Boolean success;

    ResponseCode(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Boolean getSuccess() {
        return success;
    }
}
