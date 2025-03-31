package com.mredust.mredustusersystem.exception;


import com.mredust.mredustusersystem.common.BaseResponse;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;


/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException ex) {
        log.error("BusinessException：", ex);
        return Result.fail(ResponseCode.FAIL, ex.getMessage());
    }
    
    
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException ex) {
        log.error("RuntimeException：", ex);
        return Result.fail(ResponseCode.FAIL, "服务器繁忙，请稍后重试...");
    }
    
    
    @ExceptionHandler(BindException.class)
    public BaseResponse handleMethodVoArgumentNotValidException(BindException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            FieldError error = fieldErrors.get(0);
            String repStr = Objects.requireNonNull(error.getDefaultMessage()).replace("null", "空");
            String msg = String.format("参数%s", repStr);
            return Result.fail(ResponseCode.PARAMS_ERROR, msg);
        }
        return Result.fail(ResponseCode.PARAMS_ERROR);
    }
    
}

