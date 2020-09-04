package com.markloy.code_community.advice;

import com.alibaba.fastjson.JSON;
import com.markloy.code_community.dto.ResultDTO;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, HttpServletResponse response,
                                           Throwable ex, Model model) throws IOException {
        ResultDTO resultDTO;
        if ("application/json".equals(request.getContentType())) {
            //api请求
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorResult((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.errorResult(CustomizeErrorCode.SYS_ERROR);
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultDTO));
            return null;
        }
        //自定义异常（业务异常）
        if (ex instanceof CustomizeException) {
            model.addAttribute("code", ((CustomizeException) ex).getCode());
            model.addAttribute("message", ex.getMessage());
        }
        return new ModelAndView("error");
    }

}
