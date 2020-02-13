package com.nowcoder.community.dao;

import com.nowcoder.community.entity.TbRegisterMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TbRegisterMessageMapper {
    //    根据Email和验证码来查询TbRegisterMessage表
    TbRegisterMessage selectTbRegisterMessage(String email);

    //检查Email状态，是否有已发送未验证的邮件
    int selectTbRegisterMessageByEmailStatus(String email);


    // 插入验证码信息
    int insertRegisterMessage(TbRegisterMessage selectTbRegisterMessage);

    // 发送邮件成功后，更改status状态
    int updateRegisterMessageStatus(String email, int verifyCode, int status);

    //用户注册成功后，更改usable状态
    int updateRegisterMessageUsable(String email, int verifyCode, int usable);
}
