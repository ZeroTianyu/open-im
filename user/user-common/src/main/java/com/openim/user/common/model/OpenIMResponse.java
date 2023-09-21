
package com.openim.user.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openim.user.common.enums.ResultCode;
import com.openim.user.common.enums.ResultMessage;

import java.text.MessageFormat;


public class OpenIMResponse<E> {
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("data")
    private E data;

    public static <E> OpenIMResponse SUCCESS() {
        return SUCCESS((ResultCode) ResultMessage.SUCCESS, (Object) null);
    }

    public static <E> OpenIMResponse SUCCESS(E data) {
        return SUCCESS((ResultCode) ResultMessage.SUCCESS, data);
    }

    public static <E> OpenIMResponse SUCCESS(String message, E data) {
        return new OpenIMResponse(ResultMessage.SUCCESS.getCode(), message, true, data);
    }

    public static <E> OpenIMResponse SUCCESS(ResultCode resultCode, E data) {
        return new OpenIMResponse(resultCode, data);
    }

    public static <E> OpenIMResponse SUCCESS(int code, String message, E data) {
        return new OpenIMResponse(code, message, true, data);
    }

    public static <E> OpenIMResponse FAIL() {
        return new OpenIMResponse(ResultMessage.UNKNOW_ERROR);
    }

    public static <E> OpenIMResponse FAIL(Exception e) {
        OpenIMResponse response = FAIL((ResultCode) ResultMessage.UNKNOW_ERROR);
        response.setMessage(MessageFormat.format("{0}: {1}", e.getClass().getSimpleName(), e.getMessage()));
        return response;
    }

    public static <E> OpenIMResponse FAIL(ResultCode resultCode) {
        return new OpenIMResponse(resultCode);
    }

    public static <E> OpenIMResponse FAIL(int code, String message) {
        return new OpenIMResponse(code, message, false, (Object) null);
    }

    public static <E> OpenIMResponse FAIL(ResultCode resultCode, E data) {
        return new OpenIMResponse(resultCode, data);
    }

    public static <E> OpenIMResponse FAIL(String message, E data) {
        return new OpenIMResponse(5055, message, false, data);
    }

    public static <E> OpenIMResponse FAIL(String message) {
        return new OpenIMResponse(5055, message, false, null);
    }

    public static <E> OpenIMResponse FAIL(int code, String message, E data) {
        return new OpenIMResponse(code, message, false, data);
    }

    public static <E> OpenIMResponse build(int code, String message, Boolean success) {
        return new OpenIMResponse(code, message, success);
    }

    public static <E> OpenIMResponse build(ResultCode resultCode) {
        return new OpenIMResponse(resultCode);
    }

    public static <E> OpenIMResponse build(int code, String message, Boolean success, E data) {
        return new OpenIMResponse(code, message, success, data);
    }

    public OpenIMResponse() {
    }

    public OpenIMResponse(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.getSuccess();
        this.setData((E) null);
    }

    public OpenIMResponse(ResultCode resultCode, E data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.getSuccess();
        this.setData(data);
    }

    public OpenIMResponse(int code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.setData((E) null);
    }

    public OpenIMResponse(int code, String message, Boolean success, E data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.setData(data);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMessage());
    }
}
