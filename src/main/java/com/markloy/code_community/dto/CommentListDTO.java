package com.markloy.code_community.dto;

import com.markloy.code_community.pojo.User;
import lombok.Data;

@Data
public class CommentListDTO {

    private Long id;
    private String content;
    private Long parentId;
    private Long creator;
    private Integer type;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
    private long childCommentCount;
}
