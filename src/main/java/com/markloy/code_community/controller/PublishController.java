package com.markloy.code_community.controller;

import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.dto.TagDTO;
import com.markloy.code_community.enums.TagType;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.Tag;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import com.markloy.code_community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionService qs;

    @Autowired
    private TagService ts;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return  "redirect:/";
        }
        //查询一级标签
        List<Tag> parentTag = ts.selectParentTag(TagType.TAG_TYPE_PARENT);
        //二级标签
        List<Tag> childTag = ts.selectChildTag(TagType.TAG_TYPE_CHILD, 1L);
        TagDTO tagDTOS = new TagDTO();
        tagDTOS.setParentTag(parentTag);
        tagDTOS.setChildTag(childTag);
        model.addAttribute("tagList", tagDTOS);
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Long id,
                       HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return  "redirect:/";
        }
        //查询一级标签
        List<Tag> parentTag = ts.selectParentTag(TagType.TAG_TYPE_PARENT);
        //二级标签
        List<Tag> childTag = ts.selectChildTag(TagType.TAG_TYPE_CHILD, 1L);
        TagDTO tagDTOS = new TagDTO();
        tagDTOS.setParentTag(parentTag);
        tagDTOS.setChildTag(childTag);
        model.addAttribute("tagList", tagDTOS);

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
        //查询一级标签
        List<Tag> parentTag = ts.selectParentTag(TagType.TAG_TYPE_PARENT);
        //二级标签
        List<Tag> childTag = ts.selectChildTag(TagType.TAG_TYPE_CHILD, 1L);
        TagDTO tagDTOS = new TagDTO();
        tagDTOS.setParentTag(parentTag);
        tagDTOS.setChildTag(childTag);
        model.addAttribute("tagList", tagDTOS);

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

        //验证标签
        String qua = ts.isQualified(tag);
        if (!StringUtils.isEmpty(qua)) {
            model.addAttribute("message", qua);
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
