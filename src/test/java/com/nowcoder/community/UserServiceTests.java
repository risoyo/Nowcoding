package com.nowcoder.community;

import com.nowcoder.community.service.UserService;
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

    @Test
    public void testSelectUserByName() {
        System.out.println(userService.findUserByName("kkk23456"));
    }
<<<<<<< HEAD

    @Test
    public void testLoginVerifyUser() {
        System.out.println(userService.loginVerifyUser("testuser", "123451"));
    }
=======
>>>>>>> 80e36198a323989a6bcc124e50eb32a80aae3605
}
