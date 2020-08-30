package com.markloy.code_community.config;

import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取浏览器cookie数组
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //获取token
                    User user = userMapper.findByToken(cookie.getValue());
                    if (user != null) {
                        //说明用户已登录,将用户存入session
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return true;
    }
}
