package com.nowcoder.community.service;

import com.nowcoder.community.common.returnMessage;
import com.nowcoder.community.dao.TbRegisterMessageMapper;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.entity.TbRegisterMessage;
import com.nowcoder.community.entity.UserRegistRequest;
import com.nowcoder.community.util.BizException;
import com.nowcoder.community.util.MailClient;
import com.nowcoder.community.util.NowcodingErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterService {
    private final TbRegisterMessageMapper tbRegisterMessageMapper;
    private final MailClient mailClient;
    private final ReturnService returnService;
    private final UserService userService;
    private ReturnMessage<?> returnMap;//定义变量returnMap，用于接收返回结构体


    @Autowired
    private RegisterService(TbRegisterMessageMapper tbRegisterMessageMapper, MailClient mailClient, ReturnService returnService, UserService userService) {
        this.tbRegisterMessageMapper = tbRegisterMessageMapper;
        this.mailClient = mailClient;
        this.returnService = returnService;
        this.userService = userService;
    }

    //    生成验证码并存入数据库
    public returnMessage generateVerifyCode(String email) {
        TbRegisterMessage tbRegisterMessage = new TbRegisterMessage();//初始化实体类结构体
        int verifyCode;//声明验证码
        int emailStatus;//声明邮箱状态变量，若以此邮箱查询到status状态为1的邮箱，则代表已发送邮件
        emailStatus = tbRegisterMessageMapper.selectTbRegisterMessageByEmailUsable(email);
        if (emailStatus != 0) {//为1时邮件已发送，直接返回信息
            throw new BizException(NowcodingErrCode.MAIL_SENDED.respCode(), NowcodingErrCode.MAIL_SENDED.respMessage());
        }
        tbRegisterMessage.setEmail(email);
        verifyCode = (int) ((Math.random() * 9 + 1) * 100000);//生成一个随机6位数存入verifyCode
        tbRegisterMessage.setVerifyCode(verifyCode);
        tbRegisterMessage.setVerifyMessage("验证码是【" + verifyCode + "】");
        tbRegisterMessage.setStatus(0);
        tbRegisterMessage.setUsable(0);
        tbRegisterMessage.setCreateTime(new Date());
        int affectRow = tbRegisterMessageMapper.insertRegisterMessage(tbRegisterMessage);//定义affectRow用于接收变动行数
        if (affectRow == 1) {//若变动行数为1，则已正常插入
//            returnMap = returnService.success();
            return returnMessage.success();
        } else {//若变动行数非1，则插入异常
            throw new BizException(NowcodingErrCode.VERIFY_CODE_GEN_FAIL.respCode(), NowcodingErrCode.VERIFY_CODE_GEN_FAIL.respMessage());
        }
//        return returnMap;

    }

    // 根据邮箱获取验证码
    public TbRegisterMessage getVerifyCode(String email) {
        return tbRegisterMessageMapper.selectTbRegisterMessage(email);
    }

    // 生成验证码并发送
    public returnMessage generateVerifyCodeAndSend(String email) {
        returnMessage response = generateVerifyCode(email);
        if (!response.getRespCode().equals("000000")) {//调用发送验证码方法，若响应码非000000，则调用失败
            return returnMessage.fail(NowcodingErrCode.VERIFY_CODE_GEN_FAIL.respCode(),NowcodingErrCode.VERIFY_CODE_GEN_FAIL.respMessage());
        }
        int sendStatus;//定义t变量sendStatus，0-成功发送，1-发送失败
        TbRegisterMessage tbRegisterMessage = getVerifyCode(email);
        String verifyMessage = tbRegisterMessage.getVerifyMessage();//定义verifyMessage存储验证信息
        int verifyCode = tbRegisterMessage.getVerifyCode();//定义verifyCode存储验证码
//        sendStatus = mailClient.sendMail(email, verifyMessage, verifyMessage);
//        if (sendStatus == 0) {//发送邮件成功
//            returnMap = returnService.success();
//            tbRegisterMessageMapper.updateRegisterMessageStatus(email, verifyCode, 1);//发送成功时将发送信息置为1
//        } else {
//            throw new BizException(NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respCode(), NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respMessage());
//        }
        try {
            mailClient.sendMail(email, verifyMessage, verifyMessage);
            tbRegisterMessageMapper.updateRegisterMessageStatus(email, verifyCode, 1);//发送成功时将发送信息置为1
            return returnMessage.success();
        }catch (BizException e){
            throw new BizException(NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respCode(), NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respMessage());
        }


//        return returnMap;
    }

    // 验证验证码正确后注册用户
    public returnMessage userRegister(UserRegistRequest userInfo) {
        String userName = userInfo.getName();
        String password = userInfo.getPass();
        String email = userInfo.getEmail();
        int verifyCode = userInfo.getVerifyCode();

        TbRegisterMessage tbRegisterMessage = getVerifyCode(email);
        int status;//定义status，接收insertUser()返回的影响行数
        if (tbRegisterMessage.getUsable() == 1) {//若验证码使用状态为1，则该验证码已使用，不能再注册
            throw new BizException(NowcodingErrCode.REGISTER_FAIL.respCode(), NowcodingErrCode.REGISTER_FAIL.respMessage());
        }
        if (verifyCode == tbRegisterMessage.getVerifyCode()) {//验证码正确
            //调用insert用户的service
            status = userService.insertUser(userName, password, email);
            if (status == 1) {//若status为1，则用户注册成功
                tbRegisterMessageMapper.updateRegisterMessageUsable(email, verifyCode, 1);//插入成功时将使用信息置为1
                return returnMessage.success();
            } else {//status非1，则注册失败
                throw new BizException(NowcodingErrCode.REGISTER_FAIL.respCode(), NowcodingErrCode.REGISTER_FAIL.respMessage());
            }
        } else {
            throw new BizException(NowcodingErrCode.VERIFY_CODE_ERROR.respCode(), NowcodingErrCode.VERIFY_CODE_ERROR.respMessage());
        }
    }

}
