package com.mir.demo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    //https://blog.csdn.net/weixin_37162010/article/details/80210993

    static  String SECRET = "JKKLJOoasdlfj";
    static int calendarField = Calendar.SECOND;
    static int calendarInterval;

    //静态变量calendarInterval spring无法注入值，只能给个set方法注入值
    @Value("${project.auth.tonkenlength}")
    public void setCalendarInterval(int calendarInterval) {
        JwtTokenUtil.calendarInterval = calendarInterval;
    }


    public static  String createToken(String userName) throws Exception {
        Date iatDate = new Date();

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP")
                .withClaim("exp", expiresDate)     //过期时间
                .withClaim("iat", iatDate)       //签发时间
                .withClaim("userName",userName)    //用户名
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;

    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }
    public static void main(String[] s)throws Exception{
        System.out.println("-------------------------------------------");
        long userid = 123123;
        //System.out.println(createToken(userid));
        Map<String, Claim> claims =verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJpc3MiOiJTZXJ2aWNlIiwidXNlck5hbWUiOiLmtbfkvKYiLCJleHAiOjE1MzYxMzAwMDYsImlhdCI6MTUzNjEyNjQwNn0.Zb1SSliq_XIgpWJUaMf53lMX_5UloWEGhLnFL9TVSIk");
        Claim user_id_claim = claims.get("userName");

        System.out.println(user_id_claim.asString());
        System.out.println("-------------------------------------------");
    }


}
