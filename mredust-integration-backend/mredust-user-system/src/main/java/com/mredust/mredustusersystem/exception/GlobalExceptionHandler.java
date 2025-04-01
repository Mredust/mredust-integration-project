package com.mredust.mredustusersystem.exception;


import com.mredust.mredustusersystem.common.BaseResponse;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


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
    
    
}

