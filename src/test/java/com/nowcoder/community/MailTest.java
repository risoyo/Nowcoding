package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class MailTest {
    @Autowired
    private MailClient mailClient;

    @Test
public void mailTest(){
//        调用mailClient.sendMail方法，送入三个参数：收件人，信息标题，信息内容
    mailClient.sendMail("risoyo@163.com","验证码","您的验证码是【12345】");
}
}
