package com.nowcoder.community.dao;

import com.nowcoder.community.entity.TbRegisterMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TbRegisterMessageMapper {
    //    根据Email和验证码来查询TbRegisterMessage表
    TbRegisterMessage selectTbRegisterMessage(String email);

    int insertRegisterMessage(TbRegisterMessage selectTbRegisterMessage);
}
