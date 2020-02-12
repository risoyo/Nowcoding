package com.nowcoder.community.controller;

import com.nowcoder.community.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;//注册相关Service

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(path = "/getVerifyCode")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public Map<String, Object> generateVerifyCodeAndSend(@RequestBody Map<String, Object> emailJson, HttpServletRequest request) {
        Map<String, Object> returnMap;//定义变量returnMap，用于接收返回结构体
        String email = (String) emailJson.get("email");
        returnMap = registerService.generateVerifyCodeAndSend(email);
        return returnMap;
    }

    @RequestMapping(path = "/userRegist")
    @ResponseBody
    public Map<String, Object> registUser(@RequestBody Map<String, Object> userInfo, HttpServletRequest request) {
        Map<String, Object> returnMap;//定义变量returnMap，用于接收返回结构体
        String userName = (String) userInfo.get("name");
        String password = (String) userInfo.get("pass");
        String email = (String) userInfo.get("email");
        int verifyCode = (int) userInfo.get("verifyCode");
        returnMap = registerService.userRegister(userName, password, email, verifyCode);
        return returnMap;
    }

}
