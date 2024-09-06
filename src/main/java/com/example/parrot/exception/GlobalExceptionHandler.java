package com.example.parrot.exception;

import com.example.parrot.result.BaseResponse;
import com.example.parrot.result.ErrorCode;
import com.example.parrot.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> handleBusinessException(BusinessException e){
        return ResultUtil.failure(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> handleRuntimeException(RuntimeException e){
        log.error(e.getMessage());
        return ResultUtil.failure(ErrorCode.SYSTEM_ERROR);
    }
}
