package com.openim.user.common.exception;

import com.openim.user.common.enums.ResultCode;

import java.text.MessageFormat;

public class OpenIMException extends AbstractOpenIMException {
    private Integer code = 5055;
    private String message;
    private Boolean success;

    public OpenIMException() {
    }

    public OpenIMException(Throwable cause) {
        super(cause);
    }

    public OpenIMException(String message) {
        super(message);
        super.setCode(this.code);
        this.message = message;
    }

    public OpenIMException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.message = resultCode.getMessage();
        super.setCode(this.code);
        super.setMessage(this.message);
    }

    public OpenIMException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public OpenIMException(String message, Throwable cause) {
        super(cause, message, new Object[0]);
        super.setCode(this.code);
        this.message = message;
    }

    public OpenIMException(Integer code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public OpenIMException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
        this.code = code;
        this.message = message;
    }

    public OpenIMException(String pattern, Object... params) {
        super(pattern, params);
        super.setCode(this.code);
        this.message = MessageFormat.format(pattern, params);
    }

    public OpenIMException(Throwable cause, String pattern, Object... params) {
        super(pattern, new Object[]{params, cause});
        super.setCode(this.code);
        this.message = MessageFormat.format(pattern, params);
    }
}
