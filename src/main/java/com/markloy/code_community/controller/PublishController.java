package com.markloy.code_community.controller;

import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper qm;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title, String description, String tag,
                            HttpServletRequest request, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "请先登录");
            return  "publish";
        }
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("message", "请填写标题");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("message", "请填写内容");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("message", "请填写标签");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        qm.addQuestion(question);

        return "redirect:/";
    }
}
