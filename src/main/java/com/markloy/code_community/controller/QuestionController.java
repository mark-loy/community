package com.markloy.code_community.controller;

import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService qs;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id, Model model) {
        QuestionDTO dto = qs.findById(id);
        model.addAttribute("question", dto);
        //增加浏览数
        qs.incViewCount(id);
        return "question";
    }

}
