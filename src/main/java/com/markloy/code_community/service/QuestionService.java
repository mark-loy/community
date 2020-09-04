package com.markloy.code_community.service;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.pojo.Question;

public interface QuestionService {

    PageDTO findAll(Integer currentPage, Integer count, Integer size);


    PageDTO findByUserId(Long userId, Integer currentPage, Integer count, Integer size);

    QuestionDTO findById(Long id);

    void createOrUpdate(Question question);

    int incViewCount(Long id);
}
