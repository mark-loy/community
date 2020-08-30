package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from question where creator=#{userId} limit #{count}, #{size}")
    List<Question> findByUserId(@Param("userId") Integer userId, @Param("count") int count, @Param("size") Integer size);

    @Select("select count(*) from question where creator=#{userId}")
    int findCountByUserId(Integer userId);

    @Select("select * from question where id=#{id}")
    Question findById(Integer id);

    @Update("update question set title=#{title}, description=#{description}, tag=#{tag}, gmt_modify=#{gmtModify} where id=#{id}")
    void update(Question question);
}
