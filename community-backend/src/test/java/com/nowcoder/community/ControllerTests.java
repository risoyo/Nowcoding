package com.nowcoder.community;

import com.nowcoder.community.controller.HomeController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class ControllerTests {

    @Autowired
    private HomeController homeController;

//    @Test
//    public void testHomeController(){
//        List<Map<String,Object>> indexPostList = homeController.getIndexPost();
//        System.out.println(indexPostList);
//    }
}
