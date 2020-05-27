package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.GetVerifyCodeRequest;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.entity.UserRegistRequest;
import com.nowcoder.community.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = {"注册接口"})
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;//注册相关Service
    private ReturnMessage<?> returnMap;//定义变量returnMap，用于接收返回结构体

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(path = "/getVerifyCode", method = RequestMethod.POST)//定义请求url
    @ApiOperation(value = "获取验证码", notes = "", httpMethod = "POST")
    @ResponseBody//定义返回类型为自定义类型
    @PassToken
    public returnMessage generateVerifyCodeAndSend(@RequestBody GetVerifyCodeRequest verifyCodRequest, HttpServletRequest request) {
        String email = verifyCodRequest.getEmail();
        return registerService.generateVerifyCodeAndSend(email);
    }

    @RequestMapping(path = "/userRegist")
    @ApiOperation(value = "用户注册", notes = "", httpMethod = "POST")
    @ResponseBody
    @PassToken
    public returnMessage registUser(@RequestBody UserRegistRequest userInfo, HttpServletRequest request) {

        return registerService.userRegister(userInfo);
    }

}
