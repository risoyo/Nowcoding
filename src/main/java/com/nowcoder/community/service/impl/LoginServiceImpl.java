package com.nowcoder.community.service.impl;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.LoginResponse;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.LoginService;
import com.nowcoder.community.service.RedisService;
import com.nowcoder.community.util.BizException;
import com.nowcoder.community.util.JWTUtils;
import com.nowcoder.community.util.NowcodingErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 登录的接口实现类
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final UserMapper userMapper;
    private final RedisService redisService;
    private final JWTUtils jwtUtils;

    @Autowired
    private LoginServiceImpl(UserMapper userMapper, RedisService redisService, JWTUtils jwtUtils) {
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.jwtUtils = jwtUtils;
    }


    /**
     * 登录的接口实现
     *
     * @param userName 用户名
     * @param password 密码q
     * @return 登录的实体类
     */
    @Override
    public LoginResponse loginVerifyUser(String userName, String password) {
        User userInfo = userMapper.selectByName(userName);
        LoginResponse response = new LoginResponse();
        if (userInfo == null) {//若userInfo为空，则用户不存在
            throw new BizException(NowcodingErrCode.USER_NEXIST.respCode(), NowcodingErrCode.USER_NEXIST.respMessage());
        }
        if (userInfo.getPassword().equals(password)) {//密码正确，返回成功
            String token = jwtUtils.generateToken(userName, password); // 使用jwtUtils生成随机字符串作为token
//            redisService.set(token,userName); //将token存入redis
            response.setToken(token);
            response.setHeaderUrl(userInfo.getHeaderUrl());
        } else {
            throw new BizException(NowcodingErrCode.PASS_ERROR.respCode(), NowcodingErrCode.PASS_ERROR.respMessage());
        }
        return response;

    }

    /**
     * token验证，在redis中取token
     *
     * @param token 待验证的token
     * @return 验证结果，通过返回0，否则返回1
     */
    public int tokenVerify(String token) {
        Object name = redisService.get(token);
        System.out.println(name);
        if (Objects.isNull(name)) {
            return 1;
        } else {
            return 0;
        }
    }
}
