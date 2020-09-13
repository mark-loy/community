package com.markloy.code_community.dto;

import com.markloy.code_community.pojo.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class QuestionDTO{
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
