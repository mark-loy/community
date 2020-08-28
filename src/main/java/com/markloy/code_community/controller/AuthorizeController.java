package com.markloy.code_community.controller;

import com.alibaba.fastjson.JSON;
import com.markloy.code_community.dto.AccessTokenDTO;
import com.markloy.code_community.dto.GithubUser;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider gp;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callBack")
    public String callBack(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setRedirect_uri(redirectUri);
        dto.setState(state);
        //获取token
        String gitToken = gp.getAccessToken(dto);
        //根据token获取用户信息
        GithubUser githubUser = gp.getUser(gitToken);
        if (githubUser != null) {
            //登录成功，设置session
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setLoginName(githubUser.getLogin());
            //设置本应用的用户token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.addUser(user);
            //将token回写浏览器
            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/";
    };

}
