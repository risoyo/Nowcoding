package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    // offset-每页起始行行号

    int selectDiscussPostRows(@Param("userId") int userId);
    //@Param是参数的别名，用于将较长的变量取一个短小的别名，但是当一个动态sql方法只传入一个参数，这个参数又可能不存在的时候，必须加@Param


}
