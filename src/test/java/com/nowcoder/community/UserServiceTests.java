package com.nowcoder.community;

import com.nowcoder.community.service.UserService;
import com.nowcoder.community.service.impl.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Test
    public void testSelectUserByName() {
        System.out.println(userService.findUserByName("kkk23456"));
    }

    @Test
    public void testLoginVerifyUser() {
        System.out.println(loginServiceImpl.loginVerifyUser("testuser", "12345"));
    }
}
