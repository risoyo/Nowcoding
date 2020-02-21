package com.nowcoder.community.service;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    private final DiscussPostService discussPostService;
    private final UserService userService;

    @Autowired
    public HomeService(DiscussPostService discussPostService, UserService userService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
    }

    public void getIndexPosts(int currentPageNumber,int maxRowsPerPage){
        int totalPageNumber;//前台页面显示的总页数
        int offSet;//前台页面显示的起始行

        Page page = new Page();
        page.setCurrentPageNumber(currentPageNumber);//将前台url传入的currentPageNumber传入page
        page.setRowsMaxCount(discussPostService.findDiscussPostRows(0));//调用discussPostService的方法查出共有多少条数据
        page.setmaxRowsPerPage(maxRowsPerPage);//将前台URL传入的maxRowsPerPage传入page
        page.setPath("/getIndexPost");
        totalPageNumber = page.getTotalPageNumber();//从page获取总页数
        offSet = page.getOffSet();//从page获取此页开始行数


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
//        return indexPostList;
    }
}
