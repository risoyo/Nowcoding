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
import java.util.List;

@Service
public class HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
    private final DiscussPostService discussPostService;
    private final UserService userService;

    @Autowired
    public HomeService(DiscussPostService discussPostService, UserService userService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
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


        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, offSet, maxRowsPerPage);
        List<IndexPostResponse> postList = new ArrayList<>();
        IndexPostResponse response = new IndexPostResponse();

//        将查询出的本页帖子数据存入List中
        for (DiscussPost discussPost : discussPosts) {
            String userName = userService.findUserById(discussPost.getUserId()).getUsername();
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
            logger.debug("时间是 " + sdf.format(discussPost.getCreateTime()) );
        }
        // TODO: 更改前端对应的字段，之前是message，现在是respInfo
        return returnMessage.success(postList,totalPageNumber);
    }
}
