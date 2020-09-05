package com.markloy.code_community.controller;

import com.markloy.code_community.dto.CommentDTO;
import com.markloy.code_community.dto.ResultDTO;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.pojo.Comment;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService cs;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        System.out.println(commentDTO.toString());
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCreator(user.getId());
        cs.insertComment(comment);
        return ResultDTO.successResult();
    }

}
