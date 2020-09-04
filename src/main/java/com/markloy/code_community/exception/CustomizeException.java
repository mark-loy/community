package com.markloy.code_community.exception;

import com.markloy.code_community.enums.IErrorCode;

public class CustomizeException extends RuntimeException {

    private final Integer code;
    private final String message;

    public CustomizeException(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage(){
            return message;
    }

    public Integer getCode() {
        return code;
    }
}
