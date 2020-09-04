package com.markloy.code_community.enums;

public enum CustomizeErrorCode implements IErrorCode {
    NO_LOGIN(2001, "用户未登录"),
    QUESTION_NOT_FOUND(2002, "查找的问题不存在"),
    SYS_ERROR(2003, "系统异常"),
    PARAM_EXCEPTION(2004,"参数异常"),
    COMMENT_NOT_FOUND(2005, "查找的回复不存在");

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private final Integer code;
    private final String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
