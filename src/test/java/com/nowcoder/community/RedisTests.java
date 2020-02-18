package com.nowcoder.community;

import com.nowcoder.community.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class RedisTests {
    @Autowired
    private RedisService redisService;

    @Test
    public void redisTests(){
        redisService.set("username","123123");
        System.out.println(redisService.get("username"));
        redisService.delete("username");
        System.out.println(redisService.get("username"));

    }
}
