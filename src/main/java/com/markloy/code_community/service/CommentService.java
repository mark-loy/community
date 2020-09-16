package com.markloy.code_community.service;

import com.markloy.code_community.dto.CommentListDTO;
import com.markloy.code_community.enums.CommentType;
import com.markloy.code_community.pojo.Comment;
import com.markloy.code_community.pojo.User;

import java.util.List;

public interface CommentService {

    void insertComment(Comment comment);

    List<CommentListDTO> findById(Long id, CommentType questionType);

    int incCommentLikeCount(Long id, User user);
}
