package com.markloy.code_community.enums;

public enum NotificationStatusEnum {
    READ(2, "已读"),
    UNREAD(1, "未读");

    private Integer status;
    private String msg;

    NotificationStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
