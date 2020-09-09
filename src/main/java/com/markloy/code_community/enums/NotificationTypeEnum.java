package com.markloy.code_community.enums;

public enum NotificationTypeEnum {
    QUESTION_TYPE(2, "回复了问题"),
    COMMENT_TYPE(1,"回复了评论");

    private Integer type;
    private String msg;

    NotificationTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
