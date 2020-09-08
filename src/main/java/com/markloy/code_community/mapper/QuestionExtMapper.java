package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {

    int incViewCount(Question question);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);
}
