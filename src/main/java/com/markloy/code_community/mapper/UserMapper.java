package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into user(account_id,login_name,token,gmt_create,gmt_modify, avatar_url) " +
            "values(#{accountId},#{loginName},#{token},#{gmtCreate},#{gmtModify}, #{avatarUrl})")
    void addUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}
