package com.openim.common.utils;


import com.openim.common.enums.ResultCode;
import com.openim.common.enums.ResultMessage;
import com.openim.common.exception.AbstractOpenIMException;
import com.openim.common.model.OpenIMResponse;

/**
 * @Description: 返回工具集
 * @Author Created by yan.x on 2020-01-12 .
 **/
public class ResultTool {

    /**
     * 成功时调用
     *
     * @return
     */
    public static <E> OpenIMResponse success() {
        return success(ResultMessage.SUCCESS, null);
    }

    /**
     * 成功时调用
     *
     * @return
     */
    public static <E> OpenIMResponse success(String message) {
        return success(message, null);
    }

    /**
     * 成功时调用
     *
     * @param data
     * @return
     */
    public static <E> OpenIMResponse success(E data) {
        return success(ResultMessage.SUCCESS, data);
    }

    /**
     * 成功时调用
     *
     * @param message
     * @param data
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse success(String message, E data) {
        return OpenIMResponse.SUCCESS(message, data);
    }

    /**
     * 成功时调用
     *
     * @param resultCode
     * @param data
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse success(ResultCode resultCode, E data) {
        return OpenIMResponse.SUCCESS(resultCode, data);
    }

    /**
     * 异常时调用
     *
     * @param data
     * @return
     */
    public static <E> OpenIMResponse success(int code, String message, E data) {
        return OpenIMResponse.SUCCESS(code, message, data);
    }

    /**
     * 异常时调用
     *
     * @return
     */
    public static <E> OpenIMResponse fail() {
        return OpenIMResponse.FAIL();
    }

    /**
     * 异常时调用
     *
     * @param resultCode
     * @return
     */
    public static <E> OpenIMResponse fail(ResultCode resultCode) {
        return OpenIMResponse.FAIL(resultCode);
    }

    /**
     * 异常时调用
     *
     * @param code
     * @param message
     * @return
     */
    public static <E> OpenIMResponse fail(int code, String message) {
        return OpenIMResponse.FAIL(code, message);
    }

    /**
     * 异常时调用
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static <E> OpenIMResponse fail(ResultCode resultCode, E data) {
        return OpenIMResponse.FAIL(resultCode, data);
    }

    /**
     * 异常时调用
     *
     * @param data
     * @return
     */
    public static <E> OpenIMResponse fail(String message, E data) {
        return OpenIMResponse.FAIL(message, data);
    }

    /**
     * 异常时调用
     *
     * @param data
     * @return
     */
    public static <E> OpenIMResponse fail(int code, String message, E data) {
        return OpenIMResponse.FAIL(code, message, data);
    }

    public static <E> OpenIMResponse fail(AbstractOpenIMException e) {
        return OpenIMResponse.FAIL(e);
    }

    public static <E> OpenIMResponse fail(AbstractOpenIMException e, E data) {
        OpenIMResponse fail = OpenIMResponse.FAIL(e);
        fail.setData(data);
        return fail;
    }

    public static <E> OpenIMResponse fail(Exception e) {
        return OpenIMResponse.FAIL(e);
    }


    public static <E> OpenIMResponse fail(Exception e, E data) {
        OpenIMResponse fail = OpenIMResponse.FAIL(e);
        fail.setData(data);
        return fail;
    }


    /**
     * 参数验证异常
     *
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse vaild_fail() {
        return build(ResultMessage.VALIDATE_ERROR);
    }

    /**
     * 参数验证异常
     *
     * @param message
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse vaild_fail(String message) {
        OpenIMResponse response = build(ResultMessage.VALIDATE_ERROR);
        response.setMessage(message);
        return response;
    }


    /**
     * FEIGN调用异常时
     *
     * @return
     */
    public static <E> OpenIMResponse feign_fail() {
        return OpenIMResponse.FAIL(ResultMessage.FEIGN_FAILURE);
    }

    /**
     * FEIGN调用异常时
     *
     * @param message
     * @return
     */
    public static <E> OpenIMResponse feign_fail(String message) {
        OpenIMResponse response = feign_fail();
        response.setMessage(message);
        return response;
    }

    /**
     * 构建
     *
     * @param code
     * @param message
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse<E> build(int code, String message, Boolean success) {
        return OpenIMResponse.build(code, message,success);
    }

    /**
     * 构建
     *
     * @param resultCode
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse<E> build(ResultCode resultCode) {
        return OpenIMResponse.build(resultCode);
    }

    /**
     * 构建
     *
     * @param code
     * @param message
     * @param data
     * @param <E>
     * @return
     */
    public static <E> OpenIMResponse<E> build(int code, String message, Boolean success, E data) {
        return OpenIMResponse.build(code, message, success, data);
    }

}
