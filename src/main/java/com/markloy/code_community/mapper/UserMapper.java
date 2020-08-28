package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into user(account_id,login_name,token,gmt_create,gmt_modify) " +
            "values(#{accountId},#{loginName},#{token},#{gmtCreate},#{gmtModify})")
    void addUser(User user);

    @Select("select * from user where token=#{token}")
    @Results(
        value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "loginName", column = "login_name"),
            @Result(property = "token", column = "token"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify")
        }
    )
    User findByToken(@Param("token") String token);
}
