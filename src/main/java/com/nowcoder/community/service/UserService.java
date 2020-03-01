package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
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
        String defaultAvatar = Constants.DEFAULT_AVATAR;
        userToInsert.setHeaderUrl(defaultAvatar);
        userToInsert.setCreateTime(new Date());
        return userMapper.insertUser(userToInsert); // 返回影响行数，若为1则正常插入
    }

    /**
     * 更新用户的头像url
     *
     * @param userId    用户的userID
     * @param headerUrl 头像的url
     * @return 影响行数（1-正常）
     */
    public int updateHeader(int userId, String headerUrl) {
        return userMapper.updateHeader(userId, headerUrl);
    }

}
