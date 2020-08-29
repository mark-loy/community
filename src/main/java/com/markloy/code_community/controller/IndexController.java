package com.markloy.code_community.controller;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService qs;

    @GetMapping("/")
    public String index(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            HttpServletRequest request, Model model) {
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
                    }
                    break;
                }
            }
        }

        //当前记录数
        int count = (currentPage - 1)*size;
        //获取列表信息
        PageDTO all = qs.findAll(currentPage, count, size);
        model.addAttribute("pages", all);
        return "index";
    }
}
