package com.lib4j.pager;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JWT token串结构: header.payload.signature
 * signature=HMACSHA256(base64UrlEncode(header) + "."
 * +base64UrlEncode(payload),Secret)
 *
 * token保存在客户端，每次请求传到后端，服务端只保留密钥,不要把密钥放在header和payload中；
 *
 * header中默认传递参数：
 * {"typ":"JWT","alg":"HS256"}
 *
 * payload官方定义包含属性如下（非强制）：
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 * payload 自定义数据:存放我们想放在token中存放的key-value值
 */
public class Jwt {

    public final static String HeaderName="Authorization-Jwt";

    /**
     * 过期时间
     */
    private static long EXPIRE_TIMEMILLS = 6000;

    /**
     * jwt 密钥
     */
    private static String SECRET = "---com.lib4j.jwt---";

    /**
     * 创建jwt token信息
     * 
     * @param data
     * @return Result<String>
     */
    public static Result<String> create(Map<String, Object> data) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create().withHeader(data)// 可自定义传递参数
                    .withIssuedAt(new Date())// 签发时间
                    .withPayload(data)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIMEMILLS))
                    .sign(algorithm);
            return Result.Success(token);

        } catch (JWTCreationException e) {
            e.printStackTrace();
            return Result.Failed(509, e.getMessage());
        }

    }

    /**
     * 检查并获取jwt数据
     * 
     * @param token
     * @return Result<Map<String, Object>>
     */
    public static Result<Map<String, Object>> checkAndGet(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String payload = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getPayload()));
            Map<String, Object> map = JSON.parseObject(payload, Map.class);
            return Result.Success(map);
        } catch (JWTVerificationException exception) {
            return Result.Failed(403);
        }
    }

}
