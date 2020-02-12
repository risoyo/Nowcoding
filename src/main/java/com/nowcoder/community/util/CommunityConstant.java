package com.nowcoder.community.util;

public interface CommunityConstant {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    //    注册失败,用户名已存在
    String REGISTER_FAILURE_USERNAME_EXIST = "400103";
    //成功
    String SUCCESS = "000000";
    // 验证码发送失败
    String VERIFY_CODE_SEND_FAIL = "400102";
    //验证码生成失败
    String VERIFY_CODE_GEN_FAIL = "400101";


}
