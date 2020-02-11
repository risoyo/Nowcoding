package com.nowcoder.community.controller;

import com.nowcoder.community.entity.TbRegisterMessage;
import com.nowcoder.community.service.RegisterService;
import com.nowcoder.community.util.MailClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;//验证码相关Service

    @Autowired
    private MailClient mailClient;//发送邮件工具类

    @RequestMapping(path = "/getVerifyCode")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public Map<String,Object> generateVerifyCodeAndSend(@RequestBody Map<String,Object> emailJson, HttpServletRequest request){
        int generateStatus = 0;//定义变量generateStatus，0-成功生成，1-生成失败
        Map<String,Object> returnMap = new HashMap<>();//声明返回结构体
        String email = (String)emailJson.get("email");
        if(email.length()==0){
            returnMap.put("status",1);
            returnMap.put("reason","Email地址为空，请输入Email");
            return returnMap;
        }
        generateStatus = registerService.generateVerifyCode(email);
        if(generateStatus == 0){//成功生成验证码，进行发送操作
            int sendStatus = 0;//定义变量sendStatus，0-成功发送，1-发送失败
            TbRegisterMessage tbRegisterMessage = registerService.getVerifyCode(email);
            String verifyMessage = tbRegisterMessage.getVerifyMessage();
            sendStatus = mailClient.sendMail(email,verifyMessage,verifyMessage);
            if(sendStatus == 0){
                returnMap.put("status",0);
                returnMap.put("reason","验证码已发送");
            }
            else {
                returnMap.put("status",2);
                returnMap.put("reason","验证码发送失败，请重试");
            }

        }
        else{
            returnMap.put("status",1);
            returnMap.put("reason","生成验证码失败，请重试");
        }//生成验证码失败，请重试
        return returnMap;
    }
}
