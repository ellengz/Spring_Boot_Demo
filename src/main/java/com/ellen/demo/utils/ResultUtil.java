package com.ellen.demo.utils;

import com.ellen.demo.domain.Result;
import com.ellen.demo.enums.ResultEnum;
import com.ellen.demo.exception.StuffException;

/**
 * create Result
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error (StuffException stuffException) {
        Result result = new Result();
        result.setCode(stuffException.getCode());
        result.setMsg(stuffException.getMessage());
        return result;
    }
}
