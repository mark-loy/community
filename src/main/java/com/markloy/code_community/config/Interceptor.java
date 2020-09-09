package com.markloy.code_community.config;

import com.markloy.code_community.enums.NotificationStatusEnum;
import com.markloy.code_community.mapper.NotificationMapper;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.NotificationExample;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper nm;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取浏览器cookie数组
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //获取token
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(cookie.getValue());
                    List<User> users = userMapper.selectByExample(userExample);
                    if (!users.isEmpty()) {
                        //说明用户已登录,将用户存入session
                        request.getSession().setAttribute("user", users.get(0));
                        //将该用户的未读通知数量存入session
                        NotificationExample notificationExample = new NotificationExample();
                        notificationExample.createCriteria()
                                .andReceiverIdEqualTo(users.get(0).getId())
                                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
                        request.getSession().setAttribute("notificationCount", nm.countByExample(notificationExample));
                        break;
                    }
                }
            }
        }
        return true;
    }
}
