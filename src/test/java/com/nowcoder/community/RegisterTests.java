package com.nowcoder.community;

import com.nowcoder.community.dao.TbRegisterMessageMapper;
import com.nowcoder.community.entity.TbRegisterMessage;
import com.nowcoder.community.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class RegisterTests {
    @Autowired
    private TbRegisterMessageMapper tbRegisterMessageMapper;
    @Autowired
    private RegisterService registerService;

    @Test
    public void testVerifyCodeSelect() {
        TbRegisterMessage tbRegisterMessage = tbRegisterMessageMapper.selectTbRegisterMessage("risoyo@163.com");
        System.out.println((int) ((Math.random() * 9 + 1) * 100000));
        System.out.println(tbRegisterMessage);
        System.out.println(tbRegisterMessage.getVerifyCode());
    }

    @Test
    public void testSelectTbRegisterMessageByEmailStatus() {
        System.out.println(tbRegisterMessageMapper.selectTbRegisterMessageByEmailStatus("risoyo@163.com"));
    }


    @Test
    public void testInsertVerifyMessage() {
        TbRegisterMessage tbRegisterMessage = new TbRegisterMessage();
        tbRegisterMessage.setEmail("risoyo@163.com");
        tbRegisterMessage.setVerifyCode(23445);
        tbRegisterMessage.setVerifyMessage("验证码是【23445】");
        tbRegisterMessage.setStatus(0);
        tbRegisterMessage.setUsable(0);
        tbRegisterMessage.setCreateTime(new Date());
        int affectRow = tbRegisterMessageMapper.insertRegisterMessage(tbRegisterMessage);
        System.out.println(affectRow);
        System.out.println(tbRegisterMessageMapper.selectTbRegisterMessage("risoyo@163.com"));
    }

    @Test
    public void testGenerateVerifyCode() {
        System.out.println(registerService.generateVerifyCode("risoyo@163.com"));
    }

    @Test
    public void testGetVerifyCode() {
        TbRegisterMessage tbRegisterMessage = registerService.getVerifyCode("risoyo@163.com");
        System.out.println(tbRegisterMessage);
    }

    @Test
    public void testChangeStatusAndUsable() {
        tbRegisterMessageMapper.updateRegisterMessageStatus("risoyo@163.com", 123456, 1);
        tbRegisterMessageMapper.updateRegisterMessageUsable("risoyo@163.com", 123456, 1);
    }

}
