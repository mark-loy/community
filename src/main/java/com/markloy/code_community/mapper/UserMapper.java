package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into user(account_id,login_name,token,gmt_create,gmt_modify, avatar_url, bio) " +
            "values(#{accountId},#{loginName},#{token},#{gmtCreate},#{gmtModify}, #{avatarUrl}, #{bio})")
    void addUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Insert("update user set login_name=#{loginName}, token=#{token}, gmt_modify=#{gmtModify}, avatar_url=#{avatarUrl}, bio=#{bio}  where account_id=#{accountId}")
    void updateUser(User user);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(String accountId);
}
