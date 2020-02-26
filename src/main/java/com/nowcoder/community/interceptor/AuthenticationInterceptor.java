package com.nowcoder.community.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.annotation.RequireToken;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.BizException;
import com.nowcoder.community.util.NowcodingErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;
    ReturnService returnService;

    @Override//实现一个HandlerInterceptor接口
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        else{
            if (token == null) {
                System.out.println("catcher");
                throw new BizException(NowcodingErrCode.TOKEN_NEXIST.respCode(),NowcodingErrCode.TOKEN_NEXIST.respMessage());
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(RequireToken.class)) {
            RequireToken requireToken = method.getAnnotation(RequireToken.class);
            if (requireToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new BizException(NowcodingErrCode.TOKEN_NEXIST.respCode(),NowcodingErrCode.TOKEN_NEXIST.respMessage());
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.findUserById(Integer.parseInt(userId));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}