package com.markloy.code_community.service.impl;

import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper um;

    @Override
    public void modifyUser(User user) {
        User baseUser = um.findByAccountId(user.getAccountId());
        if (baseUser != null) {
            //更新人员信息
            baseUser.setGmtModify(System.currentTimeMillis());
            baseUser.setLoginName(user.getLoginName());
            baseUser.setAvatarUrl(user.getAvatarUrl());
            baseUser.setBio(user.getBio());
            baseUser.setToken(user.getToken());
            um.updateUser(baseUser);
        } else {
            //新增人员信息
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            um.addUser(user);
        }
    }
}
