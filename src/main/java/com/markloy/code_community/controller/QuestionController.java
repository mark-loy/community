package com.markloy.code_community.controller;

import com.markloy.code_community.dto.CommentListDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.service.CommentService;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService qs;

    @Autowired
    private CommentService cs;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id, Model model) {
        //问题列表
        QuestionDTO questionDTO = qs.findById(id);
        model.addAttribute("question", questionDTO);
        //增加浏览数
        qs.incViewCount(id);
        //回复列表
        List<CommentListDTO> commentListDTO = cs.findById(id);
        model.addAttribute("commentList", commentListDTO);
        return "question";
    }

}
