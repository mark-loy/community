package com.markloy.code_community.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.markloy.code_community.dto.ResultDTO;
import com.markloy.code_community.dto.TagDTO;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.enums.TagType;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.mapper.TagMapper;
import com.markloy.code_community.mapper.UserTagMapper;
import com.markloy.code_community.pojo.*;
import com.markloy.code_community.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService ts;

    @Autowired
    private UserTagMapper utm;

    @ResponseBody
    @GetMapping("/tabTag/{id}")
    public ResultDTO<Object> tabTag(@PathVariable("id") Long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        //查询一级标签
        List<Tag> parentTag = ts.selectParentTag(TagType.TAG_TYPE_PARENT);
        //二级标签
        List<Tag> childTag = ts.selectChildTag(TagType.TAG_TYPE_CHILD, id);
        //用户自定义标签
        UserTagExample userTagExample = new UserTagExample();
        userTagExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UserTag> userTags = utm.selectByExample(userTagExample);

        TagDTO tagDTOS = new TagDTO();
        tagDTOS.setParentTag(parentTag);
        tagDTOS.setChildTag(childTag);
        tagDTOS.setUserTags(userTags);
        return new ResultDTO<>().successResultData(tagDTOS);
    }

    @PostMapping("/addTag")
    @ResponseBody
    public ResultDTO<Object> addTag(@RequestBody String tagName, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        //对json数据进行处理
        String name = JSON.parseObject(tagName).getString("tagName");
        UserTag record = new UserTag();
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtModified(System.currentTimeMillis());
        record.setTagName(name);
        record.setUserId(user.getId());
        int i = utm.insertSelective(record);
        ResultDTO<Object> resultDTO = null;
        if (i > 0) {
            resultDTO  = new ResultDTO<>().successResultData(record);
        } else {
            resultDTO = new ResultDTO<>().errorResult(400, "添加标签失败");
        }
        return resultDTO;
    }

}
