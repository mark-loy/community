package com.markloy.code_community.service;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.User;

import java.util.List;

public interface QuestionService {

    PageDTO<QuestionDTO> findAll(Integer currentPage, Integer count, Integer size,String search, String tag);


    PageDTO<QuestionDTO> findByUserId(Long userId, Integer currentPage, Integer count, Integer size);

    QuestionDTO findById(Long id);

    void createOrUpdate(Question question);

    int incViewCount(Long id,User user);

    List<Question> selectRelated(QuestionDTO questionDTO);

    PageDTO<QuestionDTO> findByCommentCount(Integer currentPage, int count, Integer size);

    PageDTO<QuestionDTO> findHotQuestion(Integer currentPage, int count, Integer size, int day);

    int incLikeCount(Long id, User user);
}
