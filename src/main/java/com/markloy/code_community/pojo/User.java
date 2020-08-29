package com.markloy.code_community.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String loginName;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String bio;
    private String avatarUrl;
}
