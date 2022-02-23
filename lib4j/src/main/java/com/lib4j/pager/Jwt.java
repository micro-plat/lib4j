package com.lib4j.pager;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// import org.apache.commons.codec.binary.Base64;
// import org.apache.commons.codec.binary.StringUtils;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.JWTVerifier;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTCreationException;
// import com.auth0.jwt.exceptions.JWTVerificationException;
// import com.auth0.jwt.interfaces.DecodedJWT;

// /**
//  * JWT token串结构: header.payload.signature
//  * signature=HMACSHA256(base64UrlEncode(header) + "."
//  * +base64UrlEncode(payload),Secret)
//  *
//  * token保存在客户端，每次请求传到后端，服务端只保留密钥,不要把密钥放在header和payload中；
//  *
//  * header中默认传递参数：
//  * {"typ":"JWT","alg":"HS256"}
//  *
//  * payload官方定义包含属性如下（非强制）：
//  * iss: jwt签发者
//  * sub: jwt所面向的用户
//  * aud: 接收jwt的一方
//  * exp: jwt的过期时间，这个过期时间必须要大于签发时间
//  * nbf: 定义在什么时间之前，该jwt都是不可用的.
//  * iat: jwt的签发时间
//  * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
//  * payload 自定义数据:存放我们想放在token中存放的key-value值
//  */
// public class Jwt {

//     /**
//      * 过期时间
//      */
//     private long EXPIRE_TIMEMILLS = 6000;

//     /**
//      * jwt 密钥
//      */
//     private String SECRET = "jwt_secret";

//     public String create() throws Exception {
//         try {
//             Algorithm algorithm = Algorithm.HMAC256(SECRET);

//             Map<String, Object> headerMap = new HashMap<String, Object>();
//             headerMap.put("date", "2022-01-01 18:00");
//             headerMap.put("where", "城东小树林");
//             String token = JWT.create().withHeader(headerMap)// 可自定义传递参数
//                     // .withIssuer("auth0")//签发者
//                     .withIssuedAt(new Date())// 签发时间
//                     .withSubject("subject").withAudience("100102134")
//                     .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIMEMILLS))
//                     // payload中加入自定义数据
//                     .withClaim("name", "小明").withClaim("introduce", "TTT").sign(algorithm);
//             System.out.println("当前时间：" + new Date());
//             System.out.println("jwt token:" + token);
//             return token;
//         } catch (JWTCreationException exception) {
//             // Invalid Signing configuration / Couldn't convert Claims.
//             throw exception;
//         }
//     }

//     public Boolean verify(String token) throws Exception {
//         try {
//             Algorithm algorithm = Algorithm.HMAC256(SECRET);
//             JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
//             DecodedJWT jwt = verifier.verify(token);

//             String decodeHeader = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getHeader()));
//             String decodePayload = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getPayload()));

//             String signature = jwt.getSignature();
//             String name = jwt.getClaim("name").asString();
//             String introduce = jwt.getClaim("introduce").asString();

//             System.out.println("header:" + jwt.getHeader());
//             System.out.println("payload:" + jwt.getPayload());
//             System.out.println("signature:" + signature);

//             System.out.println("headerString:" + decodeHeader);
//             System.out.println("payloadString:" + decodePayload);

//             System.out.println("name:" + name);
//             System.out.println("introduce:" + introduce);
//             return true;
//         } catch (JWTVerificationException exception) {
//             System.out.println("当前时间：" + new Date());
//             System.out.println("验证token失败：" + exception.getMessage());
//             return false;
//         }
//     }

// }
