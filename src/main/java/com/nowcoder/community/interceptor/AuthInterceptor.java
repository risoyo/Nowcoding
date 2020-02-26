package com.nowcoder.community.interceptor;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.BizException;
import com.nowcoder.community.util.JWTUtils;
import com.nowcoder.community.util.NowcodingErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private ReturnService returnService;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;

    @Override//实现一个HandlerInterceptor接口
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
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
        boolean val = jwtUtils.verifyToken(token);
        if(!jwtUtils.verifyToken(token)){
            throw new BizException(NowcodingErrCode.TOKEN_NVALUE.respCode(),NowcodingErrCode.TOKEN_NVALUE.respMessage());
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