package com.nowcoder.community.service;

import com.nowcoder.community.entity.LoginResponse;

/**
 * 登录的接口类
 * @author risoyo
 */
public interface LoginService {

    /**
     * 登录的实现
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录的实体类
     */
    LoginResponse loginVerifyUser(String userName, String password);
}
