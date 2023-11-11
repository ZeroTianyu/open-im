package com.openim.user.exception;


import com.openim.user.enums.ResultCode;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * @Description: 基础异常类，不可被实例化
 * @Author Created by yan.x on 2019-05-27 .
 **/
public abstract class AbstractOpenIMException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    @Getter
    private Integer code = 5000;
    private String message = "服务异常,请稍后再试!";
    @Getter
    private Boolean success = false;

    public AbstractOpenIMException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.getSuccess();
    }

    public AbstractOpenIMException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public AbstractOpenIMException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public AbstractOpenIMException(Throwable cause) {
        super(cause);
    }

    public AbstractOpenIMException(String message) {
        super(message);
        this.message = message;
    }

    public AbstractOpenIMException() {
        super("crisps framework base异常");
    }

    public AbstractOpenIMException(Throwable cause, String pattern, Object... params) {
        super(MessageFormat.format(pattern, params), cause);
        this.message = MessageFormat.format(pattern, params);
    }

    public AbstractOpenIMException(String pattern, Object... params) {
        super(MessageFormat.format(pattern, params));
        this.message = MessageFormat.format(pattern, params);
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}