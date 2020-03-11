package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.HomeService;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final HomeService homeService;
    private final JsonUtils jsonUtils;
    private final ReturnService returnService;

    @Autowired
    public HomeController(HomeService homeService, JsonUtils jsonUtils, ReturnService returnService) {
        this.homeService = homeService;
        this.jsonUtils = jsonUtils;
        this.returnService = returnService;
    }


    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ResponseBody
    @PassToken
    public ReturnMessage<?> getIndexPost(
            @RequestParam(name = "currentPageNumber", required = false, defaultValue = "1") int currentPageNumber,
            @RequestParam(name = "maxRowsPerPage", required = false, defaultValue = "10") int maxRowsPerPage
    ) {
        return homeService.getIndexPosts(currentPageNumber, maxRowsPerPage);
    }

//    @RequestMapping(path = "/jsonReturn", method = RequestMethod.GET)//定义请求url
//    @ResponseBody//定义返回类型为自定义类型
//    @PassToken
//    public ReturnMessage<Object> jsonReturn() {
//        Map<String, Object> message = new HashMap<>();
//        message.put("message1", 1111);
//        message.put("message2", 2222);
//        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
//        indexPostList.add(message);
//        message.put("message3", 3333);
//        message.put("message4", 4444);
//        indexPostList.add(message);
//        return returnService.successWithObjectAndMessage("10", indexPostList);
//    }

    @RequestMapping(path = "/jsonReturn")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    @PassToken
    public String jsonReturn() {
        Map<String, Object> message = new HashMap<>();
        message.put("message1", 1111);
        message.put("message2", 2222);

        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        indexPostList.add(message);
        message.put("message3", 3333);
        message.put("message4", 4444);
        indexPostList.add(message);
        return jsonUtils.getJSONString(111, "success", indexPostList);
    }
}
