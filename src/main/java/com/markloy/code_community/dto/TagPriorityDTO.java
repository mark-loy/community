package com.markloy.code_community.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Data
public class TagPriorityDTO implements Comparable<TagPriorityDTO>{

    private String tagName;
    private Integer questionCount;
    private Integer commentCount;

    @Override
    public int compareTo(@NotNull TagPriorityDTO o) {
        return (this.questionCount - o.getQuestionCount()) + (this.commentCount - o.getCommentCount());
    }
}
