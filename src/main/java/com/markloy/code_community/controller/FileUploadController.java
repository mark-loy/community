package com.markloy.code_community.controller;

import com.markloy.code_community.dto.FileUploadDTO;
import com.markloy.code_community.provider.OSSProvider;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@Controller
@Log4j2
public class FileUploadController {

    @Autowired
    private OSSProvider ossProvider;

    @ResponseBody
    @PostMapping("/fileUpload")
    public FileUploadDTO fileUpload(HttpServletRequest request) {
        //转化request对象
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile parameter = multipartRequest.getFile("editormd-image-file");
        URL url = null;
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        try {
            url = ossProvider.fileUpload(parameter);
            if (url != null) {
                fileUploadDTO.setSuccess(1);
                fileUploadDTO.setMessage("文件上传成功");
                fileUploadDTO.setUrl(url.toString());
            } else {
                fileUploadDTO.setSuccess(0);
                fileUploadDTO.setMessage("文件上传失败");
            }
        } catch (IOException e) {
            fileUploadDTO.setSuccess(0);
            fileUploadDTO.setMessage("文件上传失败");
            log.log(Level.INFO, "文件上传失败" + e);
        }
        return fileUploadDTO;
    }

}
