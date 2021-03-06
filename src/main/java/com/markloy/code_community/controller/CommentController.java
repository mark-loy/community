package com.markloy.code_community.controller;

import com.markloy.code_community.dto.CommentDTO;
import com.markloy.code_community.dto.CommentListDTO;
import com.markloy.code_community.dto.ResultDTO;
import com.markloy.code_community.enums.CommentType;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.mapper.CommentMapper;
import com.markloy.code_community.pojo.Comment;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService cs;

    @Autowired
    private CommentMapper cm;

    @PostMapping("/comment")
    @ResponseBody
    public Object addComment(@RequestBody CommentDTO commentDTO,
                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentDTO == null || StringUtils.isEmpty(commentDTO.getContent())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_CONTENT_NOT_EMPTY);
        }
        //新增评论
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

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDTO<Object> selectSubComment(@PathVariable("id") Long id) {
        List<CommentListDTO> listDTOS = cs.findById(id, CommentType.COMMENT_TYPE);
        return new ResultDTO<>().successResultData(listDTOS);
    }

    @GetMapping("/commentLike/{id}")
    @ResponseBody
    public ResultDTO<Object> commentLike(@PathVariable("id") Long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new ResultDTO<>().errorResult(CustomizeErrorCode.NO_LOGIN);
        }
        int count = cs.incCommentLikeCount(id, user);
        if (count > 0) {
            Comment comment = cm.selectByPrimaryKey(id);
            return new ResultDTO<>().successResultData(comment);
        }
        return new ResultDTO<>().errorResult(CustomizeErrorCode.ADD_LIKE_COUNT_FAIL);
    }
}
