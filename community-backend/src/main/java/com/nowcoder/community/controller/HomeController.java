package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
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
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getIndexPost", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getIndexPost() {
        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, 0, 10);
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
        return indexPostList;
    }
}
