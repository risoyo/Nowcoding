package com.nowcoder.community;

import com.nowcoder.community.dao.ReturnInfoMapper;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.util.ReturnMessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class ReturnTests {
    @Autowired
    private ReturnInfoMapper returnInfoMapper;

    @Test
    public void testReturnInfoSelect() {

        System.out.println(returnInfoMapper.selectReturnInfoByCode("400103"));
    }

    @Autowired
    private ReturnMessageUtil returnMessageUtil;

    @Test
    public void returnMessageTest(){
        ReturnMessage returnMessage = returnMessageUtil.sucess();
        System.out.println(returnMessage);
    }
}
