package com.markloy.code_community.controller;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.dto.TagPriorityDTO;
import com.markloy.code_community.schedule.HotTagSchedule;
import com.markloy.code_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService qs;

    @Autowired
    private HotTagSchedule hotTagSchedule;

    @GetMapping({"/"})
    public String index(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "id", defaultValue = "0") Integer id,
            @RequestParam(value = "hotId", defaultValue = "1") Integer hotId,
            String search, String tag, HttpServletRequest request,
            Model model) {
        //当前记录数
        int count = (currentPage - 1)*size;
        PageDTO<QuestionDTO> all = null;
        if (id == 0) {
            all = qs.findAll(currentPage, count, size, search, tag);
        }
        if (id == 1) {
            //跳转到热门页面
            all = qs.findHotQuestion(currentPage, count, size, hotId);
            model.addAttribute("hotId", hotId);
        }
        if (id == 2) {
            //跳转到零回复页面
            all = qs.findByCommentCount(currentPage, count, size);
        }
        model.addAttribute("id", id);
        model.addAttribute("pages", all);
        if (!StringUtils.isEmpty(search)) {
            //处理search(将字符串中的空格和逗号，替换为|)
            search.replaceAll(",| |，", "|");
            model.addAttribute("search", search);
        }
        //热门话题
        model.addAttribute("tags", hotTagSchedule.getTagPriorityDTOS());
        model.addAttribute("tag", tag);

        return "index";
    }

}
