package com.markloy.code_community.dto;

import lombok.Data;

@Data
public class GithubUser {

    private Long id;
    private String login;
    private String name;
    private String bio;
    private String avatarUrl;
}
