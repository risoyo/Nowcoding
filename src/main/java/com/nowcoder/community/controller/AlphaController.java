package com.nowcoder.community.controller;

import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private ReturnService returnService;

    @RequestMapping(path = "/testRequireToken")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public  ReturnMessage<?> initAuth(){
        Map<String,Object> message = new HashMap<>();
        message.put("message","hello");
        return returnService.successWithObjectAndMessage("success",message);
    }


    @RequestMapping(path = "/testReturnMessage", method = RequestMethod.GET)//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public ReturnMessage<?> initReturnMessage(){
        Map<String,Object> message = new HashMap<>();
        message.put("message1",1111);
        message.put("message2",2222);
        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        indexPostList.add(message);
        message.put("message3",1111);
        message.put("message4",2222);
        indexPostList.add(message);
        return returnService.successWithObjectAndMessage("10",indexPostList);
//        return returnMessageUtil.error(30001,"数据错误");
    }
}
