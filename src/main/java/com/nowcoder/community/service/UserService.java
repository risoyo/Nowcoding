package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService{
    private final UserMapper userMapper;
    private final ReturnService returnService;

    @Autowired
    public UserService(UserMapper userMapper, ReturnService returnService) {
        this.userMapper = userMapper;
        this.returnService = returnService;
    }

    public User findUserById(int userId) {
        return userMapper.selectById(userId);
    }

    public User findUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    public int insertUser(String userName, String password, String email) {
        User userToInsert = new User();
        if (userMapper.selectByName(userName) != null) {
            return 0;
        }
        userToInsert.setUsername(userName);
        userToInsert.setPassword(password);
        userToInsert.setSalt("abc");
        userToInsert.setEmail(email);
        userToInsert.setHeaderUrl("http://11.com");
        userToInsert.setCreateTime(new Date());
        return userMapper.insertUser(userToInsert); // 返回影响行数，若为1则正常插入
    }

}
