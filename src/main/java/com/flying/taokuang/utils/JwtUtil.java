package com.flying.taokuang.utils;

import com.flying.taokuang.dataobject.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    /**
     * 获取token
     * @param encryKey
     * @param minutes
     * @return
     */
    public static String getToken(User user, String encryKey, int minutes){
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString()) //当前用户
                .setIssuedAt(new Date()) //签发日期
                .setSubject("system") //说明
                .setIssuer("npy") //签发者信息
                .signWith(SignatureAlgorithm.HS256, encryKey) //加密方式
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .setExpiration(new Date(currentTime + minutes * 1000 * 60)) //过期时间
                .compact();
    }

    /**
     * 验证是否到时间
     * @param token
     * @param encryKey
     * @return
     */
    public static boolean isExpiration(String token, String encryKey){
        try {
            long currentTime = System.currentTimeMillis();
            if (Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody().getExpiration().after(new Date(currentTime - 1000 * 60))){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            System.out.println("异常");
            return false;
        }
    }

    /**
     * 获取claims
     * @param token
     * @param encryKey
     * @return
     */
    public static Claims getClamis(String token, String encryKey){
        try {
            Claims claims = Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
//    System.out.println(claims.get("id"));
}
