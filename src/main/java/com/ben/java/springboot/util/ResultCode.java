package com.ben.java.springboot.util;

/**
 * 系统状态码
 */
public class ResultCode {
    //默认 系统异常
    public static final int SYS_ERROR = 1000;
    //请求成功
    public static final int REQUEST_SUCCESSFUL = 0;

    //账户异常相关
    public static final int ACCOUNT_ERROR = 2000;
    //登录异常
    public static final int ACCOUNT_ERROR_LOGIN = 2100;
    //密码错误
    public static final int ACCOUNT_ERROR_LOGIN_PASSWORD = 2101;
    //账户不存在
    public static final int ACCOUNT_ERROR_LOGIN_EMPTY_ACCOUNT = 2102;




}
