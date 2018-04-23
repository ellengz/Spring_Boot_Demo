package com.ellen.demo.exception;

import com.ellen.demo.enums.ResultEnum;

public class StuffException extends RuntimeException{

    private Integer code;

    public StuffException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {

        return code;
    }
}
