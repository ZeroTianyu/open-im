package com.openim.user.controller.base;


import com.openim.user.enums.ResultCode;
import com.openim.user.enums.ResultMessage;
import com.openim.user.exception.AbstractOpenIMException;
import com.openim.user.model.OpenIMPageable;
import com.openim.user.model.OpenIMResponse;
import com.openim.user.utils.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;


public abstract class AbstractCrispsController<E> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected OpenIMResponse<E> success() {
        return ResultTool.success();
    }

    protected OpenIMResponse<E> success(String message) {
        return ResultTool.success(message, null);
    }

    protected OpenIMResponse<E> success(E entity) {
        return ResultTool.success(entity);
    }

    protected OpenIMResponse<E> success(int code, String message, E entity) {
        return ResultTool.success(code, message, entity);
    }

    protected OpenIMResponse<E> success(ResultCode resultCode, E entity) {
        return ResultTool.success(resultCode, entity);
    }

    protected OpenIMResponse<E> success(OpenIMPageable<E> pageable, List<E> entityList) {
        return ResultTool.success(pageable.setRecords(entityList));
    }

    protected OpenIMResponse<E> fail() {
        return build(ResultMessage.UNKNOW_ERROR);
    }

    protected OpenIMResponse fail(AbstractOpenIMException e) {
        return build(e.getCode(), e.getMessage(), false);
    }

    protected OpenIMResponse fail(Exception e) {
        OpenIMResponse response = fail(ResultMessage.UNKNOW_ERROR);
        response.setMessage(MessageFormat.format("{0}: {1}", e.getClass().getSimpleName(), e.getMessage()));
        return response;
    }

    protected OpenIMResponse<E> fail(String message) {
        return build(ResultMessage.UNKNOW_ERROR.getCode(), message, false);
    }

    protected OpenIMResponse<E> fail(E errorData) {
        return fail(ResultMessage.UNKNOW_ERROR, errorData);
    }

    protected OpenIMResponse<E> fail(int code, String message) {
        return ResultTool.build(code, message, false);
    }

    protected OpenIMResponse<E> fail(int code, String message, E errorData) {
        return ResultTool.fail(code, message, errorData);
    }

    protected OpenIMResponse<E> fail(ResultCode resultCode) {
        return ResultTool.fail(resultCode);
    }

    protected OpenIMResponse<E> fail(ResultCode resultCode, E errorData) {
        return ResultTool.fail(resultCode, errorData);
    }

    protected OpenIMResponse<E> build(int code, String message, Boolean success) {
        return ResultTool.build(code, message, success);
    }

    protected OpenIMResponse<E> build(ResultCode resultCode) {
        return ResultTool.build(resultCode);
    }
}
