package com.nowcoder.community;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
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
public class ServiceTests {
    @Autowired
    private UserService userService;


    @Test
    public void testUserService() {
        User user = userService.findUserById(101);
        System.out.println(user);
    }
}
