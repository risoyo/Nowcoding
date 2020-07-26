package com.nowcoder.community.service;

import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.UserRegistRequest;

/**
 * 用户注册的接口类
 * @author risoyo
 */
public interface RegisterService {
    /**
     * 生成验证码并发送
     *
     * @param email 邮箱
     * @return 验证结果
     */
    returnMessage generateVerifyCodeAndSend(String email);

    /**
     * 验证验证码正确后注册用户
     *
     * @param userInfo 用户信息
     * @return 用户创建情况
     */
    returnMessage userRegister(UserRegistRequest userInfo);
}
