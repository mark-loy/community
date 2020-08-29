package com.markloy.code_community.service;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    PageDTO findAll(Integer currentPage, Integer count, Integer size);
}
