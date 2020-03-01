package com.nowcoder.community.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    private final DateUtils dateUtils;
    private final String issuer = Constants.TOKEN_ISSUER; // 设置token签名
    private final String subject = Constants.TOKEN_SUBJECT;// 设置token主题
    private final String audience = "APP";// 设置token接受者
    private final int expireTime = Constants.TOKEN_EXPIRE_TIME;// 设置token过期时间
    private UserMapper userMapper;
    private HostHolder hostHolder;

    @Autowired
    private JWTUtils(DateUtils dateUtils, UserMapper userMapper, HostHolder hostHolder) {
        this.dateUtils = dateUtils;
        this.userMapper = userMapper;
        this.hostHolder = hostHolder;
    }

    /**
     * 生成jwt
     *
     * @param userName 生成token的参数一：用户名
     * @param password 生成token的参数二：密码
     * @return 返回生成的无Claim的token
     */
    public String generateToken(String userName, String password) {

        String secret = "secret";// token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        // 头部信息
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = dateUtils.getAfterTime(nowDate, expireTime);// 2小过期

        return JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withIssuer(issuer)//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject(subject)//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience(userName)//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(Algorithm.HMAC256(password));//签名 Signature
    }

    /**
     * 生成附带Claim的token
     *
     * @param loginName 生成token的参数一：用户名
     * @return 返回生成的附带Claim的token
     */
    public String generateTokenWithClaim(String loginName) {
        Date nowDate = new Date();
        Date expireDate = dateUtils.getAfterTime(nowDate, expireTime);// 2小过期

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create().withHeader(map)
                /* 设置 载荷 Payload */
                .withClaim("loginName", loginName)
                .withIssuer(issuer)// 签名是有谁生成 例如 服务器
                .withSubject(subject)// 签名的主题
                // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的
                .withAudience(audience)// 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) // 生成签名的时间
                .withExpiresAt(expireDate)// 签名过期的时间
                /* 签名 Signature */
                .sign(algorithm);
    }

    /**
     * 校验不带Claim的token是否正确
     *
     * @param token 传入的token
     * @return True：token正确，False：token错误
     */
    public boolean verifyToken(String token) {
        // 获取 token 中的 user id
        String userName;
        try {
            userName = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new BizException(NowcodingErrCode.TOKEN_NVALUE.respCode, NowcodingErrCode.TOKEN_NVALUE.respMessage);
        }
        User user = userMapper.selectByName(userName);
        hostHolder.setUser(user);  // 调用hostHolder来在当前线程存储用户数据
        if (user == null) {
            throw new BizException(NowcodingErrCode.TOKEN_NEXIST.respCode(), NowcodingErrCode.TOKEN_NEXIST.respMessage());
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new BizException(NowcodingErrCode.TOKEN_NVALUE.respCode, NowcodingErrCode.TOKEN_NVALUE.respMessage);
        }
        return true;

    }

    /**
     * 校验带Claim的token是否正确
     *
     * @param token     传入的token
     * @param loginName 传入的用户名
     * @return True：token正确，False：token错误
     */
    public boolean verifyTokenWithClaim(String token, String loginName) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withSubject(subject).withAudience(audience).build(); // Reusable verifier instance
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token); // 验证token，若为真返回true，若为假返回false
        } catch (JWTVerificationException e) {
            return false;
        }
        Map<String, Claim> claims = jwt.getClaims();
        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            String key = entry.getKey();
            Claim claim = entry.getValue();
            // 若loginName与password匹配token中的则返回true
            if (key.equals("loginName") && !claim.asString().equals(loginName)) {
                return false;
            }
        }
        return true;
    }
}

