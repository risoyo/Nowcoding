package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.IndexPostResponse;
import com.nowcoder.community.service.HomeService;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.util.IpUtil;
import com.nowcoder.community.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = {"主页接口"})
public class HomeController {
    private final HomeService homeService;
    private final JsonUtils jsonUtils;
    private final ReturnService returnService;
    private final IpUtil ipUtil;

    @Autowired
    public HomeController(HomeService homeService, JsonUtils jsonUtils, ReturnService returnService, IpUtil ipUtil) {
        this.homeService = homeService;
        this.jsonUtils = jsonUtils;
        this.returnService = returnService;
        this.ipUtil = ipUtil;
    }


    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ApiOperation(value = "获取首页帖子", notes = "", httpMethod = "GET")
    @ResponseBody
    @PassToken
    public returnMessage<List<IndexPostResponse>> getIndexPost(HttpServletRequest request,
                                                               @RequestParam(name = "currentPageNumber", required = false, defaultValue = "1") int currentPageNumber,
                                                               @RequestParam(name = "maxRowsPerPage", required = false, defaultValue = "10") int maxRowsPerPage
    ) {
        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);
        System.out.println("IP地址是 " + ipAddress);

//        return homeService.getIndexPosts(currentPageNumber, maxRowsPerPage);
        return homeService.getIndexPosts(currentPageNumber, maxRowsPerPage);
    }

//    @RequestMapping(path = "/jsonReturn")//定义请求url
//    @ResponseBody//定义返回类型为自定义类型
//    @PassToken
//    public String jsonReturn() {
//        Map<String, Object> message = new HashMap<>();
//        message.put("message1", 1111);
//        message.put("message2", 2222);
//
//        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
//        indexPostList.add(message);
//        message.put("message3", 3333);
//        message.put("message4", 4444);
//        indexPostList.add(message);
//        return jsonUtils.getJSONString(111, "success", indexPostList);
//    }
}
