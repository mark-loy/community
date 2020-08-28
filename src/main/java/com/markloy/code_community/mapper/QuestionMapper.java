package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modify, creator, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{tag})")
    void addQuestion(Question question);

}
