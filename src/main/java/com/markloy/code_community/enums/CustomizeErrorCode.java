package com.markloy.code_community.enums;

public enum CustomizeErrorCode implements IErrorCode {
    NO_LOGIN(2001, "用户未登录,请先登录"),
    QUESTION_NOT_FOUND(2002, "查找的问题不存在"),
    SYS_ERROR(2003, "系统异常"),
    PARAM_ERROR(2004,"参数异常"),
    COMMENT_NOT_FOUND(2005, "查找的评论不存在"),
    COMMENT_CONTENT_NOT_EMPTY(2006, "评论内容不能为空"),
    ADD_LIKE_COUNT_FAIL(2007, "增加点赞数失败");

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
