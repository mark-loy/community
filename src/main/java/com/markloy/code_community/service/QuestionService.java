package com.markloy.code_community.service;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.pojo.Question;

import java.util.List;

public interface QuestionService {

    PageDTO<QuestionDTO> findAll(Integer currentPage, Integer count, Integer size,String search);


    PageDTO<QuestionDTO> findByUserId(Long userId, Integer currentPage, Integer count, Integer size);

    QuestionDTO findById(Long id);

    void createOrUpdate(Question question);

    int incViewCount(Long id);

    List<Question> selectRelated(QuestionDTO questionDTO);
}
