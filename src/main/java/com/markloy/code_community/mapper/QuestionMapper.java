package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modify, creator, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{tag})")
    void addQuestion(Question question);

    @Select("select * from question limit #{count}, #{size}")
    List<Question> findAll(@Param("count") int count, @Param("size") Integer size);

    @Select("select count(*) from question")
    int findTotalCount();
}
