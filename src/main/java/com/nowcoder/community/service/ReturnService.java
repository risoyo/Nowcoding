package com.nowcoder.community.service;

import com.nowcoder.community.entity.ReturnMessage;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {

    /**
     * 无异常 请求成功并无具体内容返回
     *
     * @return 返回message
     */
    public ReturnMessage<Object> success() {
        return new ReturnMessage<>("000000", "成功", null, null);
    }

    /**
     * 无异常 请求成功并有具体message内容和message内容返回
     *
     * @param message 需返回的message内容
     * @return 返回ReturnMessage消息
     */
    public ReturnMessage<Object> successWithMessage(String message) {
        return new ReturnMessage<>("000000", "成功", message, null);
    }

    /**
     * 无异常 请求成功并有具体message内容和message内容返回
     *
     * @param message 需返回的message内容
     * @param object  需返回的object内容
     * @return 返回ReturnMessage消息
     */
    public ReturnMessage<Object> successWithObjectAndMessage(String message, Object object) {
        return new ReturnMessage<>("000000", "成功", message, object);
    }

    /**
     * 有自定义错误异常信息
     *
     * @param statusCode 错误码
     * @param errMessage 错误信息
     * @return 返回message
     */
    public ReturnMessage<Object> errorMessage(String statusCode, String errMessage) {
        return new ReturnMessage<>(statusCode, errMessage, null, null);
    }

}