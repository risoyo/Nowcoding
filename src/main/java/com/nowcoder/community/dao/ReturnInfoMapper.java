package com.nowcoder.community.dao;

import com.nowcoder.community.entity.ReturnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReturnInfoMapper {
    // 根据响应码查询响应信息
    ReturnInfo selectReturnInfoByCode(String respCode);
}
