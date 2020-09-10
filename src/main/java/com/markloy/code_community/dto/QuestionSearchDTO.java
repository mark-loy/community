package com.markloy.code_community.dto;

import lombok.Data;

@Data
public class QuestionSearchDTO {
    private Integer count;
    private Integer size;
    private String search;
}
