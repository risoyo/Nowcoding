package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserMapper userMapper;

    @Autowired
    private LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
