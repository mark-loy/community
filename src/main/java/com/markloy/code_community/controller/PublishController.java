package com.markloy.code_community.controller;

import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService qs;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return  "redirect:/";
        }
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Long id,
                       HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return  "redirect:/";
        }
        QuestionDTO question = qs.findById(id);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Long id, String title, String description, String tag,
                            HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return  "redirect:/";
        }

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

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
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        qs.createOrUpdate(question);
        return "redirect:/";
    }
}
