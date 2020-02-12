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
        Map<String,Object> returnMap = new HashMap<>();//声明返回结构体
        String email = (String)emailJson.get("email");
        returnMap = registerService.generateVerifyCodeAndSend(email);
        return returnMap;
    }
}
