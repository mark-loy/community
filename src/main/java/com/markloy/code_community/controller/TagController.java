package com.markloy.code_community.controller;

import com.markloy.code_community.dto.ResultDTO;
import com.markloy.code_community.dto.TagDTO;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.enums.TagType;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.mapper.TagMapper;
import com.markloy.code_community.pojo.Tag;
import com.markloy.code_community.pojo.TagExample;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService ts;

    @ResponseBody
    @GetMapping("/tabTag/{id}")
    public ResultDTO<Object> tabTag(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        //查询一级标签
        List<Tag> parentTag = ts.selectParentTag(TagType.TAG_TYPE_PARENT);
        //二级标签
        List<Tag> childTag = ts.selectChildTag(TagType.TAG_TYPE_CHILD, id);
        TagDTO tagDTOS = new TagDTO();
        tagDTOS.setParentTag(parentTag);
        tagDTOS.setChildTag(childTag);
        return new ResultDTO<>().successResultData(tagDTOS);
    }

}
