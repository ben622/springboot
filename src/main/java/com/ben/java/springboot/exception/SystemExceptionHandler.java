package com.ben.java.springboot.exception;

import com.ben.java.springboot.bean.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统异常处理
 */
@ControllerAdvice
public class SystemExceptionHandler {

    @ResponseBody
    @ExceptionHandler(SystemException.class)
    public Result<String> handlerException(SystemException e) {
        return e.getResult();
    }
}
