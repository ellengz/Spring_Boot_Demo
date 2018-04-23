package com.ellen.demo.enums;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "Unknown error"),
    SUCCESS(0, "Success"),
    QUANTITY_TOO_BIG(100, "Please choose a quantity from [0,99]"),
    QUANTITY_TOO_SMALL(101, "Please choose a quantity from [0,99]"),
    ILLEGAL_NAME(200, "Please choose an alphabetical name"),
    ID_NOT_EXIST(300, "ID not exist"),

    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
