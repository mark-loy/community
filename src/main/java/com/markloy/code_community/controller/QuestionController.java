package com.markloy.code_community.controller;

import com.markloy.code_community.dto.CommentListDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.enums.CommentType;
import com.markloy.code_community.pojo.Question;
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
        //查询问题列表
        QuestionDTO questionDTO = qs.findById(id);
        model.addAttribute("question", questionDTO);
        //相关问题查询
        List<Question> relatedQuestion = qs.selectRelated(questionDTO);
        model.addAttribute("related", relatedQuestion);
        //增加问题浏览数(待完善)
        qs.incViewCount(id);
        //查询评论列表
        List<CommentListDTO> commentListDTO = cs.findById(id, CommentType.QUESTION_TYPE);
        model.addAttribute("commentList", commentListDTO);
        return "question";
    }

}
