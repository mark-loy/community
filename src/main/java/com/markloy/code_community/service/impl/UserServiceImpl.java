package com.markloy.code_community.service.impl;

import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.pojo.UserExample;
import com.markloy.code_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper um;

    @Override
    public void modifyUser(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = um.selectByExample(example);
        if (users.isEmpty()) {
            //新增人员信息
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            um.insert(user);
        } else {
            //更新人员信息
            User baseUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModify(System.currentTimeMillis());
            updateUser.setLoginName(user.getLoginName());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setBio(user.getBio());
            updateUser.setToken(user.getToken());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(baseUser.getId());
            um.updateByExampleSelective(updateUser, userExample);
        }
    }
}
