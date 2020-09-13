package com.markloy.code_community.dto;

import com.markloy.code_community.pojo.Tag;
import com.markloy.code_community.pojo.UserTag;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private List<Tag> parentTag;
    private List<Tag> childTag;
    private List<UserTag> userTags;
}
