package com.markloy.code_community.controller;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionService qs;

    @GetMapping("/")
    public String index(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            HttpServletRequest request, Model model) {
        //当前记录数
        int count = (currentPage - 1)*size;
        //获取列表信息
        PageDTO all = qs.findAll(currentPage, count, size);
        model.addAttribute("pages", all);
        return "index";
    }
}
