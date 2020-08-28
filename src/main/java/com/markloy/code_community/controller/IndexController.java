package com.markloy.code_community.controller;

import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //获取浏览器cookie数组
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if ("token".equals(cookie.getName())) {
                //获取token
                User user = userMapper.findByToken(cookie.getValue());
                if (user != null) {
                    //说明用户已登录,将用户存入session
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
