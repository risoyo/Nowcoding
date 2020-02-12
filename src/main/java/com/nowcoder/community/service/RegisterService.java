package com.nowcoder.community.service;

import com.nowcoder.community.dao.TbRegisterMessageMapper;
import com.nowcoder.community.entity.TbRegisterMessage;
import com.nowcoder.community.util.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    private final TbRegisterMessageMapper tbRegisterMessageMapper;
    private final MailClient mailClient;

    @Autowired
    private RegisterService(TbRegisterMessageMapper tbRegisterMessageMapper, MailClient mailClient) {
        this.tbRegisterMessageMapper = tbRegisterMessageMapper;
        this.mailClient = mailClient;
    }

    //    生成验证码并存入数据库
    public int generateVerifyCode(String email) {
        TbRegisterMessage tbRegisterMessage = new TbRegisterMessage();//初始化实体类结构体
        int verifyCode;//声明验证码
        tbRegisterMessage.setEmail(email);
        verifyCode = (int) ((Math.random() * 9 + 1) * 100000);//生成一个随机6位数存入verifyCode
        tbRegisterMessage.setVerifyCode(verifyCode);
        tbRegisterMessage.setVerifyMessage("验证码是【" + verifyCode + "】");
        tbRegisterMessage.setStatus(0);
        tbRegisterMessage.setUsable(0);
        tbRegisterMessage.setCreateTime(new Date());
        int affectRow = tbRegisterMessageMapper.insertRegisterMessage(tbRegisterMessage);//定义affectRow用于接收变动行数
        if (affectRow == 1) {//若变动行数为1，则已正常插入
            return 0;
        } else {//若变动行数非1，则插入异常
            return 1;
        }

    }

    public TbRegisterMessage getVerifyCode(String email) {
        return tbRegisterMessageMapper.selectTbRegisterMessage(email);
    }

    public Map<String, Object> generateVerifyCodeAndSend(String email) {
        int generateStatus;//定义变量generateStatus，0-成功生成，1-生成失败
        Map<String, Object> returnMap = new HashMap<>();//声明返回结构体
        generateStatus = generateVerifyCode(email);
        if (generateStatus == 0) {//成功生成验证码，进行发送操作
            int sendStatus;//定义变量sendStatus，0-成功发送，1-发送失败
            TbRegisterMessage tbRegisterMessage = getVerifyCode(email);
            String verifyMessage = tbRegisterMessage.getVerifyMessage();
            sendStatus = mailClient.sendMail(email, verifyMessage, verifyMessage);
            if (sendStatus == 0) {
                returnMap.put("status", 0);
                returnMap.put("reason", "验证码已发送");
            } else {
                returnMap.put("status", 2);
                returnMap.put("reason", "验证码发送失败，请重试");
            }

        } else {
            returnMap.put("status", 1);
            returnMap.put("reason", "生成验证码失败，请重试");
        }//生成验证码失败，请重试
        return returnMap;
    }
}
