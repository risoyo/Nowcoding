package com.nowcoder.community;

import com.nowcoder.community.service.RegisterService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class RegisterControllerTests {
    @Autowired
    private RegisterService registerService;

//    @Test
//    public void userRegisterTest() {
//        registerService.userRegister("testuser", "12345", "risoyo@163.com", 711335);
//    }
}
