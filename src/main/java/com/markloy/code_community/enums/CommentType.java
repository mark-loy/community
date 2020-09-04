package com.markloy.code_community.enums;

public enum CommentType {
    COMMENT_TYPE(1),
    QUESTION_TYPE(2);

    private final Integer type;
    CommentType(Integer type) {
        this.type = type;
    }
    public Integer getType() {
        return type;
    }
    public static boolean isExist(Integer type) {

        for (CommentType commentType : CommentType.values()) {
            if (commentType.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
