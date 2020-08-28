package com.markloy.code_community.provider;

import com.alibaba.fastjson.JSON;
import com.markloy.code_community.dto.AccessTokenDTO;
import com.markloy.code_community.dto.GithubUser;
import okhttp3.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    /**
     * 发送请求
     *
     * @param url  请求地址
     * @param json 请求参数
     * @param type 请求类型
     * @return
     * @throws IOException
     */
    String send(String url, @Nullable String json, String type) {
        OkHttpClient client = new OkHttpClient();
        Request request = null;
        String string = null;
        try {
            if ("GET".equals(type)) {
                request = new Request.Builder().url(url).build();
            } else if ("POST".equals(type)) {
                RequestBody body = RequestBody.create(json, JSON);
                request = new Request.Builder().url(url).post(body).build();
            }
            Response response = client.newCall(request).execute();
            string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 获取token
     *
     * @param at
     * @return
     */
    public String getAccessToken(AccessTokenDTO at) {

        String access_token = send("https://github.com/login/oauth/access_token",
                com.alibaba.fastjson.JSON.toJSONString(at), "POST");
        //截取token
        String token = access_token.split("&")[0].split("=")[1];
        return token;

    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public GithubUser getUser(String token) {
        String json = send("https://api.github.com/user?access_token=" + token, null, "GET");
        GithubUser user = com.alibaba.fastjson.JSON.parseObject(json, GithubUser.class);
        return user;
    }
}
