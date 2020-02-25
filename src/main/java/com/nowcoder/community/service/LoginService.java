package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class LoginService implements CommunityConstant {
    private final UserMapper userMapper;
    private final ReturnService returnService;
    private final RedisService redisService;

    @Autowired
    private LoginService(UserMapper userMapper,ReturnService returnService,RedisService redisService) {
        this.userMapper = userMapper;
        this.returnService = returnService;
        this.redisService = redisService;
    }

    public ReturnMessage<?> loginVerifyUser(String userName, String password) {
        User userInfo = userMapper.selectByName(userName);
        ReturnMessage<?> returnMap;//定义变量returnMap，用于接收返回结构体
        System.out.println(userInfo);
        if (userInfo == null) {//若userInfo为空，则用户不存在
            returnMap = returnService.error(USER_NEXIST);
            return returnMap;
        }
        if (userInfo.getPassword().equals(password)) {//密码正确，返回成功
            String token = UUID.randomUUID().toString(); // 使用UUID生成随机字符串作为token
            redisService.set(token,userName); //将token存入redis
            returnMap = returnService.successWithObjectAndMessage(SUCCESS,token);
        } else {
            returnMap = returnService.error(PASS_ERROR);
        }
        return returnMap;
    }

    public int tokenVerify(String token){
        Object name = redisService.get(token);
        System.out.println(name);
        if(Objects.isNull(name)){
            return 1;
        }
        else{
            return 0;
        }
    }
}
