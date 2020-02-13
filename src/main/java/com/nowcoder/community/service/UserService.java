package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserService implements CommunityConstant {
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

    public Map<String, Object> loginVerifyUser(String userName, String password) {
        User userInfo = userMapper.selectByName(userName);
        Map<String, Object> returnMap;//定义变量returnMap，用于接收返回结构体
        System.out.println(userInfo);
        if (userInfo == null) {//若userInfo为空，则用户不存在
            returnMap = returnService.returnMessage(USER_NEXIST);
            return returnMap;
        }
        if (userInfo.getPassword().equals(password)) {//密码正确，返回成功
            returnMap = returnService.returnMessage(SUCCESS);
        } else {
            returnMap = returnService.returnMessage(PASS_ERROR);
        }
        return returnMap;
    }
}
