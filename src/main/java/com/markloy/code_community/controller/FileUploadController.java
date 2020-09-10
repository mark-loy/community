package com.markloy.code_community.controller;

import com.markloy.code_community.dto.FileUploadDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {

    @ResponseBody
    @PostMapping("/fileUpload")
    public FileUploadDTO fileUpload() {
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setSuccess(1);
        fileUploadDTO.setMessage("上传成功");
        fileUploadDTO.setUrl("http://localhost:8080/images/file1.png");
        return fileUploadDTO;
    }

}
