package com.ben.java.springboot.exception;

import com.ben.java.springboot.bean.Response;
import com.ben.java.springboot.util.ResultCode;

public abstract class SystemException extends Exception {
    private Response<String> response = new Response<>();

    public SystemException() {
        this(ResultCode.SYS_ERROR, "服务器开小差了！");
    }

    public SystemException(int code,String message) {
        response.setCode(code);
        response.setMessage(message);
    }

    public Response<String> getResponse() {
        return response;
    }
}
