package com.nowcoder.community.service;

import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.IndexPostResponse;
import com.nowcoder.community.entity.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
    private final DiscussPostService discussPostService;
    private final UserService userService;
    private final ReturnService returnService;

    @Autowired
    public HomeService(DiscussPostService discussPostService, UserService userService, ReturnService returnService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
        this.returnService = returnService;
    }


    /**
     * 获取首页指定页帖子内容的合集以及总页数
     *
     * @param currentPageNumber 当前页码
     * @param maxRowsPerPage    每页最大行数
     * @return 返回json数据，message节点为总页数，String；data节点为帖子内容，ListMap
     */
    public returnMessage<List<IndexPostResponse>> getIndexPosts(int currentPageNumber, int maxRowsPerPage) {
        String totalPageNumber;//前台页面显示的总页数
        int offSet;//前台页面显示的起始行
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Page page = new Page();
        page.setCurrentPageNumber(currentPageNumber);//将前台url传入的currentPageNumber传入page
        page.setRowsMaxCount(discussPostService.findDiscussPostRows(0));//调用discussPostService的方法查出共有多少条数据
        page.setmaxRowsPerPage(maxRowsPerPage);//将前台URL传入的maxRowsPerPage传入page
        page.setPath("/getIndexPost");
        totalPageNumber = Integer.toString(page.getTotalPageNumber());//从page获取总页数
        offSet = page.getOffSet();//从page获取此页开始行数


        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, offSet, maxRowsPerPage);
        List<IndexPostResponse> postList = new ArrayList<>();

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
            indexPost.put("create_time", sdf.format(discussPost.getCreateTime()));
            indexPost.put("comment_count", discussPost.getCommentCount());
            indexPost.put("score", discussPost.getScore());
            IndexPostResponse response = new IndexPostResponse();
            response.setId(discussPost.getId());
            response.setUser_id(discussPost.getUserId());
            response.setUser_name(userName);
            response.setTitle(discussPost.getTitle());
            response.setContent(discussPost.getContent());
            response.setType(discussPost.getType());
            response.setStatus(discussPost.getStatus());
            response.setCreate_time(discussPost.getCreateTime());
            response.setComment_count(discussPost.getCommentCount());
            response.setScore(discussPost.getScore());
            postList.add(response);
            indexPostList.add(indexPost);
            logger.debug("时间是 " + sdf.format(discussPost.getCreateTime()) );
        }
//        return returnService.successWithObjectAndMessage(totalPageNumber, indexPostList);
        return returnMessage.success(postList,totalPageNumber);
    }
}
