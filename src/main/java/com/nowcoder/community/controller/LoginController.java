package com.nowcoder.community.controller;


import com.nowcoder.community.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    private LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/Login")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public Map<String, Object> generateVerifyCodeAndSend(@RequestBody Map<String, Object> loginJson, HttpServletRequest request) {
        Map<String, Object> returnMap;//定义变量returnMap，用于接收返回结构体
        String name = (String) loginJson.get("name");
        String pass = (String) loginJson.get("pass");
        returnMap = loginService.loginVerifyUser(name, pass);
        return returnMap;
    }


}
