package com.markloy.code_community.enums;

public enum TagType {
    TAG_TYPE_PARENT("1"),
    TAG_TYPE_CHILD("2");

    private final String type;

    TagType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
