package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
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
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getIndexPost(
            @RequestParam(name = "currentPageNumber", required = false, defaultValue = "1") int currentPageNumber,
            @RequestParam(name = "maxRowsPerPage", required = false, defaultValue = "10") int maxRowsPerPage
    ) {
        int totalPageNumber = 0;//前台页面显示的总页数
        int offSet = 0;//前台页面显示的起始行

        Page page = new Page();
        page.setCurrentPageNumber(currentPageNumber);
        page.setRowsMaxCount(discussPostService.findDiscussPostRows(0));
        page.setmaxRowsPerPage(maxRowsPerPage);
        page.setPath("/getIndexPost");
        totalPageNumber = page.getTotalPageNumber();
        offSet = page.getOffSet();


        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, offSet, maxRowsPerPage);

//      将页面属性存入indexPostList
        Map<String, Object> indexPageContribute = new HashMap<>();
        indexPageContribute.put("totalPageNumber", totalPageNumber);
        indexPageContribute.put("offSet", offSet);
        indexPostList.add(indexPageContribute);

//        将查询出的本页帖子数据存入List中
        for (DiscussPost discussPost : discussPosts) {
            Map<String, Object> indexPost = new HashMap<>();//列表集合中的数据项
            String userName = userService.findUserById(discussPost.getUserId()).getUsername();
            indexPost.put("id", discussPost.getId());
            indexPost.put("user_id", discussPost.getUserId());
            indexPost.put("user_name", userName);
            indexPost.put("title", discussPost.getTitle());
            indexPost.put("content", discussPost.getContent());
            indexPost.put("type", discussPost.getType());
            indexPost.put("status", discussPost.getStatus());
            indexPost.put("create_time", discussPost.getCreateTime());
            indexPost.put("comment_count", discussPost.getCommentCount());
            indexPost.put("score", discussPost.getScore());
            indexPostList.add(indexPost);
            System.out.println(discussPost);
        }
        System.out.println(page);
        System.out.println(totalPageNumber);
        System.out.println(offSet);
        return indexPostList;
    }
}
