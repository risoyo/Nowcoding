package com.nowcoder.community;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nowcoder.community.util.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class JWTTests {
    @Autowired
    private DateUtils dateUtils;

    @Test
    public void dateTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));
        Date afterDate = dateUtils.getAfterTime(now,30);
        System.out.println(sdf.format(afterDate ));
    }

    @Test
    public void createTokenWithoutClaim() {

        String secret = "secret";// token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        // 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate =  dateUtils.getAfterTime(nowDate,30);// 2小过期

        String token = JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withIssuer("SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("this is test token")//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(algorithm);//签名 Signature
        Assert.assertTrue(token.length() > 0);
        System.out.println(token);
    }

    @Test
    public void createTokenWithClaim(){
        Date nowDate = new Date();
        Date expireDate = dateUtils.getAfterTime(nowDate,30);// 2小过期

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withHeader(map)
                /* 设置 载荷 Payload */
                .withClaim("loginName", "zhuoqianmingyue").withClaim("userName", "张三").withClaim("deptName", "技术部")
                .withIssuer("SERVICE")// 签名是有谁生成 例如 服务器
                .withSubject("this is test token")// 签名的主题
                // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的
                .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) // 生成签名的时间
                .withExpiresAt(expireDate)// 签名过期的时间
                /* 签名 Signature */
                .sign(algorithm);

        Assert.assertTrue(token.length() > 0);
        System.out.println(token);
    }


    @Test
    public void verifyTokenWithoutClaim(){
        String token = generateTokenWithoutClaim();

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build(); // Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        String subject = jwt.getSubject();
        List<String> audience = jwt.getAudience();
        System.out.println(subject);
        System.out.println(audience.get(0));

    }
    public String generateTokenWithoutClaim() {

        String secret = "secret";// token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        // 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate =  dateUtils.getAfterTime(nowDate,30);;// 2小过期

        String token = JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withIssuer("SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("this is test token")//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(algorithm);//签名 Signature
        Assert.assertTrue(token.length() > 0);
        System.out.println(token);
        return token;
    }

    @Test
    public void verifyTokenWithClaim(){
        String token = generateTokenWithClaim();

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build(); // Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        String subject = jwt.getSubject();
        List<String> audience = jwt.getAudience();
        Map<String, Claim> claims = jwt.getClaims();
        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            String key = entry.getKey();
            Claim claim = entry.getValue();
            System.out.println("key:" + key + " value:" + claim.asString());
        }
        Claim claim = claims.get("loginName");
        System.out.println(subject);
        System.out.println(audience.get(0));

    }
    public String generateTokenWithClaim() {

        Date nowDate = new Date();
        Date expireDate = dateUtils.getAfterTime(nowDate,30);// 2小过期

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withHeader(map)
                /* 设置 载荷 Payload */
                .withClaim("loginName", "zhuoqianmingyue").withClaim("userName", "张三").withClaim("deptName", "技术部")
                .withIssuer("SERVICE")// 签名是有谁生成 例如 服务器
                .withSubject("this is test token")// 签名的主题
                // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的
                .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) // 生成签名的时间
                .withExpiresAt(expireDate)// 签名过期的时间
                /* 签名 Signature */
                .sign(algorithm);

        Assert.assertTrue(token.length() > 0);
        System.out.println(token);
        return token;
    }
}
