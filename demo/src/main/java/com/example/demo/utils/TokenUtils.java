package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;

public class TokenUtils {

    public static String getToken(String userid, String password){
        String token="";
        token= JWT.create().withAudience(userid) // 将 user id 保存到 token 里面
            .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //五分钟后token过期
            .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
        return token;
    } 
}