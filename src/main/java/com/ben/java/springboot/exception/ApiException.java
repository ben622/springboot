package com.ben.java.springboot.exception;

public class ApiException extends SystemException {
    public ApiException() {
    }

    public ApiException(int code, String message) {
        super(code, message);
    }
}
