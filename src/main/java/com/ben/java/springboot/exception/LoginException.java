package com.ben.java.springboot.exception;

import com.ben.java.springboot.util.ResultCode;

/**
 * 登录异常处理
 */
public class LoginException extends SystemException {
    public LoginException() {
        super(ResultCode.ACCOUNT_ERROR_LOGIN_PASSWORD,"用户名或密码错误！");
    }
}
