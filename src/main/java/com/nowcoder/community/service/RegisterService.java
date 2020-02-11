package com.nowcoder.community.service;

import com.nowcoder.community.dao.TbRegisterMessageMapper;
import com.nowcoder.community.entity.TbRegisterMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterService {
    @Autowired
    private TbRegisterMessageMapper tbRegisterMessageMapper;

//    生成验证码并存入数据库
    public int generateVerifyCode(String email){
        TbRegisterMessage tbRegisterMessage = new TbRegisterMessage();//初始化实体类结构体
        int verifyCode = 0;//声明验证码
        tbRegisterMessage.setEmail(email);
        verifyCode = (int)((Math.random()*9+1)*100000);//生成一个随机6位数存入verifyCode
        tbRegisterMessage.setVerifyCode(verifyCode);
        tbRegisterMessage.setVerifyMessage("验证码是【"+verifyCode+"】");
        tbRegisterMessage.setStatus(0);
        tbRegisterMessage.setUsable(0);
        tbRegisterMessage.setCreateTime(new Date());
        int affectRow = tbRegisterMessageMapper.insertRegisterMessage(tbRegisterMessage);//定义affectRow用于接收变动行数
        if(affectRow == 1){//若变动行数为1，则已正常插入
            return 0;
        }
        else {//若变动行数非1，则插入异常
            return 1;
        }

    }

    public TbRegisterMessage getVerifyCode(String email){
        TbRegisterMessage tbRegisterMessage = tbRegisterMessageMapper.selectTbRegisterMessage(email);
        return tbRegisterMessage;
    }
}
