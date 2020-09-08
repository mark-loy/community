package com.markloy.code_community.dto;

import com.markloy.code_community.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {

    private List<Tag> parentTag;
    private List<Tag> childTag;
}
