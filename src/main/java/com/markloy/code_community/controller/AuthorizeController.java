package com.markloy.code_community.controller;

import com.alibaba.fastjson.JSON;
import com.markloy.code_community.dto.AccessTokenDTO;
import com.markloy.code_community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider gp;

    @GetMapping("/callBack")
    public String callBack(@RequestParam("code") String code, @RequestParam("state") String state) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id("Iv1.608c3ae1ace91189");
        dto.setClient_secret("b209f8d0f8ec98da73bf58adf497a89ea6c99e0a");
        dto.setCode(code);
        dto.setRedirect_uri("http://localhost:8080/callBack");
        dto.setState(state);
        System.out.println("json======"+JSON.toJSONString(dto));
        String token = gp.getAccessToken(dto);
        System.out.println("token====="+token);
        return "index";
    };

}
