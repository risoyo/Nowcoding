package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.PassToken;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ResponseBody
    @PassToken
    public ReturnMessage<?> getIndexPost(
            @RequestParam(name = "currentPageNumber", required = false, defaultValue = "1") int currentPageNumber,
            @RequestParam(name = "maxRowsPerPage", required = false, defaultValue = "10") int maxRowsPerPage
    ) {
        return homeService.getIndexPosts(currentPageNumber,maxRowsPerPage);
    }
}
