package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.util.JsonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
@Api(tags = {"测试接口"})
public class AlphaController {

    @Autowired
    private ReturnService returnService;
    private JsonUtils jsonUtils;

    @RequestMapping(path = "/testRequireToken")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    @PassToken
    public ReturnMessage<?> initAuth() {
        Map<String, Object> message = new HashMap<>();
        message.put("message", "hello");
        return returnService.successWithObjectAndMessage("success", message);
    }


//    @RequestMapping(path = "/jsonReturn")//定义请求url
//    @ResponseBody//定义返回类型为自定义类型
//    @PassToken
//    public String jsonReturn() {
//        Map<String, Object> message = new HashMap<>();
//        message.put("message1", 1111);
//        message.put("message2", 2222);
//        //        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
////        indexPostList.add(message);
////        message.put("message3", 1111);
////        message.put("message4", 2222);
////        indexPostList.add(message);
////        return returnService.successWithObjectAndMessage("10", indexPostList);
////        return returnMessageUtil.error(30001,"数据错误");
//        return jsonUtils.getJSONString(111, "success", message);
//    }
}
