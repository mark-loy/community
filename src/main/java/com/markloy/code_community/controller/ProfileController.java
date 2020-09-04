package com.markloy.code_community.controller;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService qs;

    @GetMapping("/profile/{active}")
    public String profile(@PathVariable("active") String active,
                          @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "size", defaultValue = "3") Integer size,
                          HttpServletRequest request, Model model) {
        //从session中获取user对象
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("question".equals(active)) {
            model.addAttribute("selector", "question");
            model.addAttribute("title", "我的问题");
        } else if ("reply".equals(active)) {
            model.addAttribute("selector", "reply");
            model.addAttribute("title", "最新回复");
        }
        //当前记录数
        int count = (currentPage - 1)*size;
        //获取列表信息
        PageDTO all = qs.findByUserId(user.getId(), currentPage, count, size);
        model.addAttribute("pages", all);
        return "profile";
    }

}
