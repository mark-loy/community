package com.markloy.code_community.service;

import com.markloy.code_community.dto.TagDTO;
import com.markloy.code_community.enums.TagType;
import com.markloy.code_community.pojo.Tag;

import java.util.List;

public interface TagService {

    List<Tag> selectParentTag(TagType tagType);

    List<Tag> selectChildTag(TagType tagType, Long parentId);

    String isQualified(String tag);
}
