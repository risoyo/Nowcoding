package com.nowcoder.community;

import com.nowcoder.community.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class LoginServiceTests {
    @Autowired
    private LoginService loginService;

    @Test
    public void tokenVerifyTest(){
        System.out.println(loginService.tokenVerify("b2b10413-263f-41cd-bc44-ebbf99846eeb"));
    }
}
