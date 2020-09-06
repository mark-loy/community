package com.markloy.code_community.dto;

import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.enums.IErrorCode;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String result;
    private T data;

    public static ResultDTO errorResult(Integer code, String result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setResult(result);
        return resultDTO;
    }

    public static ResultDTO errorResult(IErrorCode errorCode) {
        return errorResult(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO errorResult(CustomizeException exception) {
        return errorResult(exception.getCode(), exception.getMessage());
    }

    public static ResultDTO successResult() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setResult("请求成功");
        return resultDTO;
    }

    public ResultDTO<T> successResultData(T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(200);
        resultDTO.setResult("请求成功");
        resultDTO.setData(data);
        return resultDTO;
    }
}
