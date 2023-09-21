package com.openim.auth.exception.handler;


import com.openim.auth.enums.ResponseCode;
import com.openim.auth.exception.AbstractOpenIMException;
import com.openim.auth.exception.OpenIMException;
import com.openim.auth.model.OpenIMResponse;
import com.openim.auth.utils.ResultTool;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常信息处理类
 *
 * @author guoty
 **/
@Slf4j
@ControllerAdvice
public class OpenIMExceptionHandler {



    @ResponseBody
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public OpenIMResponse<Object> nonTransientDataAccessException(DataIntegrityViolationException e) {
        OpenIMResponse<Object> failed = new OpenIMResponse<>();
        log.error("DataIntegrityViolationException =====>> ", e);
        failed.setResultCode(ResponseCode.VALIDATE_ERROR);
        return failed;
    }

    /**
     * 处理参数验证异常
     * <p>
     *
     * @Size @RequestParam("name") String name
     * </p>
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public OpenIMResponse<Object> resolveConstraintViolationException(ConstraintViolationException ex) {
        log.error("参数验证异常 =====>> ", ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            List<String> messages = new LinkedList<>();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                messages.add(constraintViolation.getMessage());
            }
            return ResultTool.fail(ResponseCode.PARAMETER_ERROR.getCode(), String.join(",", messages));
        }
        return ResultTool.fail(ResponseCode.UNKNOW_ERROR);
    }

    /**
     * 处理po实体类验证异常
     * <p>
     *
     * @Valid @RequestBody VO params
     * </p>
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public OpenIMResponse<Object> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException =====>> ", ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringJoiner joiner = new StringJoiner(",");
            for (ObjectError objectError : objectErrors) {
                joiner.add(objectError.getDefaultMessage());
            }

            return ResultTool.fail(ResponseCode.UNKNOW_ERROR.getCode(), String.join("", joiner.toString()));
        }
        return ResultTool.fail(ResponseCode.UNKNOW_ERROR);
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public OpenIMResponse<Object> defaultBindExceptionHandler(BindException ex) {
        log.error("BindException =====>> ", ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringJoiner joiner = new StringJoiner(",");
            for (ObjectError objectError : objectErrors) {
                joiner.add(objectError.getDefaultMessage());
            }
            return ResultTool.vaild_fail(String.join(",", joiner.toString()));
        }
        return ResultTool.vaild_fail();
    }

    /**
     * API统一异常处理
     **/

    @ResponseBody
    @ExceptionHandler(value = AbstractOpenIMException.class)
    public OpenIMResponse<Object> customExceptionHandler(AbstractOpenIMException e) {
        log.error("AbstractCrispsException =====>> ", e);
        try {
            Throwable innerEx = e.getCause();
            while (innerEx != null) {
                if (innerEx.getCause() == null) {
                    break;
                }
                innerEx = innerEx.getCause();
            }
            int code = e.getCode() == null ? ResponseCode.UNKNOW_ERROR.getCode() : e.getCode();
            String message = e.getMessage() == null ? ResponseCode.UNKNOW_ERROR.getMessage() : e.getMessage();
            return ResultTool.fail(code, message, null);
        } catch (Exception ex) {
            log.error("", ex);
            return ResultTool.fail(ResponseCode.UNKNOW_ERROR);
        }
    }

    /**
     * API统一异常处理
     **/
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public OpenIMResponse<Object> defaultErrorHandler(Exception e) {
        OpenIMResponse<Object> failed = new OpenIMResponse<>();
        log.error("统一异常处理 =====>> ", e);

        // 请求方法“HEAD”不支持
        if (e instanceof HttpMediaTypeException) {
            failed.setResultCode(ResponseCode.NOT_FOUND);
            failed.setMessage("请求方法“HEAD”不支持");
            return failed;
        }

        // 参数类型不匹配得
        if (e instanceof MethodArgumentTypeMismatchException) {
            failed.setResultCode(ResponseCode.PARAMETER_ERROR);
            failed.setMessage("参数类型不正确");
            return failed;
        }


        // 路径不存在异常处理
        if (e instanceof NoHandlerFoundException) {
            failed.setResultCode(ResponseCode.NOT_FOUND);
            return failed;
        }

        // 请求方式错误异常处理
        if (e instanceof HttpRequestMethodNotSupportedException) {
            failed.setResultCode(ResponseCode.NOT_FOUND);
            return failed;
        }

        // 请求参数有误异常处理
        if (e instanceof MissingServletRequestParameterException) {
            failed.setResultCode(ResponseCode.PARAMETER_ERROR);
            return failed;
        }

        // 请求参数转换异常处理
        if (e instanceof HttpMessageNotReadableException) {
            failed.setResultCode(ResponseCode.PARAMETER_ERROR);
            return failed;
        }

        //参数绑定异常处理
        if (e instanceof BindException) {
            failed.setResultCode(ResponseCode.PARAMETER_ERROR);
            return failed;
        }

        //无权访问
        if (e instanceof AccessDeniedException) {
            failed.setResultCode(ResponseCode.ACCESS_DENIED_ERROR);
            return failed;
        }

        //无权访问
        if (e instanceof OpenIMException) {
            failed.setCode(((OpenIMException) e).getCode());
            failed.setMessage(e.getMessage());
            return failed;
        }
        failed.setResultCode(ResponseCode.UNKNOW_ERROR);
        failed.setMessage(ResponseCode.UNKNOW_ERROR.getMessage());
        return failed;
    }
}
