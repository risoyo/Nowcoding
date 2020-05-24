package com.nowcoder.community.controller;


import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.LoginRequest;
import com.nowcoder.community.entity.LoginResponse;
import com.nowcoder.community.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    private LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/login")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    @PassToken
    public returnMessage userLogin(@Validated @RequestBody LoginRequest loginData, HttpServletRequest request) {
        // TODO 添加生成Token并返回前台，添加拦截器，在前台访问url时拦截进行token校验，校验通过则放行，校验不通过强制返回
        String name = loginData.getName();
        String pass = loginData.getPass();
        System.out.println("name is " + name + "; pass is " + pass);
        LoginResponse response =  loginService.loginVerifyUser(name, pass);
        return returnMessage.success(response);
    }


}
