package com.ben.java.springboot.exception;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.util.ResultCode;

public abstract class SystemException extends Exception {
    private Result<String> result = new Result<>();

    public SystemException() {
        this(ResultCode.SYS_ERROR, "服务器开小差了！");
    }

    public SystemException(int code,String message) {
        result.setCode(code);
        result.setMsg(message);
    }

    public Result<String> getResult() {
        return result;
    }
}
