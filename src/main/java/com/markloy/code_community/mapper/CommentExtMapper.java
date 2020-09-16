package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentExtMapper {

    int incCommentLikeCount(Comment recordCommentLike);

}
