package com.nowcoder.community.util;

public enum  NowcodingErrCode {
    /**
     * 默认warning响应
     */
    DEFAULT_WARNING("000002","警告"),
    /**
     * 默认error错误
     */
    DEFAULT_ERROR("999999","错误"),
    /**
     * 默认成功
     */
    DEFAULT_SUCCESS("000000","成功"),
    /**
     * 注册失败,用户名已存在
      */
    REGISTER_FAILURE_USERNAME_EXIST("400103","注册失败,用户名已存在"),
    /**
     * 验证码发送失败
     */
    VERIFY_CODE_SEND_FAIL("400102","验证码发送失败"),
    /**
     * 验证码生成失败
     */
    VERIFY_CODE_GEN_FAIL("400101","验证码生成失败"),
    /**
     * 验证码错误
     */
    VERIFY_CODE_ERROR("400104","验证码错误"),
    /**
     * 注册失败，未知错误
     */
    REGISTER_FAIL("400105","注册失败，未知错误"),
    /**
     * 发送失败，邮件已发送
     */
    MAIL_SENDED("400106","发送失败，邮件已发送"),
    /**
     * 登录失败，用户不存在
     */
    USER_NEXIST("400201","登录失败，用户不存在"),
    /**
     * 登录失败，密码错误
     */
    PASS_ERROR("400202","登录失败，密码错误"),
    /**
     * Token信息不存在
     */
    TOKEN_NEXIST("400301","Token信息不存在"),
    /**
     * Token无效
     */
    TOKEN_NVALUE("400302","Token无效"),
    /**
     * 头像上传失败
     */
    UPLOAD_FAIL("400401","头像上传失败"),
    SUCCESS("111","222");


    String respCode;
    String respMessage;
    NowcodingErrCode(String respCode, String respMessage){
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public String respCode(){
        return respCode;
    }

    public String respMessage(){
        return respMessage;
    }
}
