package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.IndexPostResponse;
import com.nowcoder.community.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@Controller
//@ResponseBody
// 在此处写上responseBody，里面的方法就不用逐个写了
@RestController
// 可以用RestController代替上面两个注解
@Api(tags = {"主页接口"})
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ApiOperation(value = "获取首页帖子", notes = "", httpMethod = "GET")
    @PassToken
    public returnMessage<List<IndexPostResponse>> getIndexPost(HttpServletRequest request,
                                                               @RequestParam(name = "currentPageNumber", required = false, defaultValue = "1") int currentPageNumber,
                                                               @RequestParam(name = "maxRowsPerPage", required = false, defaultValue = "10") int maxRowsPerPage
    ) {
        return homeService.getIndexPosts(currentPageNumber, maxRowsPerPage);
    }

}
