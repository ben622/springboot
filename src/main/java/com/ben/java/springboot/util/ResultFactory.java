package com.ben.java.springboot.util;

import com.ben.java.springboot.bean.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ResultFactory {
    public static <T> Result obtainResultByPage(@NotNull Page<T> page) {
        return obtainResult(ResultCode.SUCCESSFUL, "成功", (int) page.getTotalElements(), page.getContent());
    }

    public static <T> Result obtainResultBySuccessful(int count, List<T> t) {
        return obtainResult(ResultCode.SUCCESSFUL, "成功", count, t);
    }

    public static <T> Result obtainResultBySuccessful(int count, T t) {
        return obtainResult(ResultCode.SUCCESSFUL, "成功", count, t);
    }


    public static <T> Result obtainResultByFailure(int count, T t) {
        return obtainResult(ResultCode.FAILURE, "失败", count, t);
    }

    public static <T> Result obtainResultByFailure(int count, List<T> t) {
        return obtainResult(ResultCode.FAILURE, "失败", count, t);
    }

    public static <T> Result obtainResult(int code, String msg, int count, Object response) {
        return new Result(code, count, msg, response);
    }


}
