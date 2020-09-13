package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.TagDTO;
import com.markloy.code_community.enums.TagType;
import com.markloy.code_community.mapper.TagMapper;
import com.markloy.code_community.mapper.UserTagMapper;
import com.markloy.code_community.pojo.*;
import com.markloy.code_community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tm;

    @Override
    public List<Tag> selectParentTag(TagType tagType) {
        //查询一级标签
        TagExample example = new TagExample();
        example.createCriteria()
                .andTypeEqualTo(tagType.getType());
        List<Tag> parentTag = tm.selectByExample(example);
        return parentTag;
    }

    @Override
    public List<Tag> selectChildTag(TagType tagType, Long parentId) {
        //二级标签
        TagExample tagExample = new TagExample();
        tagExample.createCriteria()
                .andTypeEqualTo(tagType.getType())
                .andParentIdEqualTo(parentId);
        List<Tag> childTag = tm.selectByExample(tagExample);
        return childTag;
    }

    @Override
    public String isQualified(String tag) {
        String result = "";
        //标签不能有重复的
        String[] split = tag.split(",");
        HashSet<String> set = new HashSet<>();
        for (String s : split) {
            set.add(s);
        }
        if (split.length != set.size()) {
            result = "请勿填写重复标签";
            return result;
        }
        //标签需存在在标签库
        TagExample tagExample = new TagExample();
        tagExample.createCriteria()
                .andTypeEqualTo(TagType.TAG_TYPE_CHILD.getType());
        List<Tag> tagList = tm.selectByExample(tagExample);
        ArrayList<String> arrayList = new ArrayList<>();
        for (Tag tagName : tagList) {
            arrayList.add(tagName.getTagName());
        }
        for (String s : set) {
            if (!arrayList.contains(s)) {
                result = "请选择标签库中的标签或新建标签";
                break;
            }
        }
        return result;
    }
}
