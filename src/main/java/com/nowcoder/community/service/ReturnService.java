package com.nowcoder.community.service;

import com.nowcoder.community.dao.ReturnInfoMapper;
import com.nowcoder.community.entity.ReturnInfo;
import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReturnService implements CommunityConstant {
    private final ReturnInfoMapper returnInfoMapper;

    @Autowired
    private ReturnService(ReturnInfoMapper returnInfoMapper) {
        this.returnInfoMapper = returnInfoMapper;
    }

    public Map<String, Object> returnMessage(String statusCode) {
        Map<String, Object> returnMap = new HashMap<>();
        ReturnInfo returnInfo = returnInfoMapper.selectReturnInfoByCode(statusCode);
        returnMap.put("resp_code", returnInfo.getRespCode());
        returnMap.put("resp_info", returnInfo.getRespInfo());
        return returnMap;
    }

    public Map<String, Object> returnToken(String statusCode,String token) {
        Map<String, Object> returnMap = new HashMap<>();
        ReturnInfo returnInfo = returnInfoMapper.selectReturnInfoByCode(statusCode);
        returnMap.put("resp_code", returnInfo.getRespCode());
        returnMap.put("resp_info", returnInfo.getRespInfo());
        returnMap.put("token",token);
        return returnMap;
    }

    /**
     * 无异常 请求成功并有具体内容返回
     * @param object 需返回的内容
     * @return 返回message
     */
//    public ReturnMessage<?> returnWithMessageAndObject(String statusCode,String message,Object object){
//        Map<String, Object> returnMap = new HashMap<>();
//        ReturnInfo returnInfo = returnInfoMapper.selectReturnInfoByCode(statusCode);
//        returnMap.put("resp_code", returnInfo.getRespCode());
//        returnMap.put("resp_info", returnInfo.getRespInfo());
//        returnMap.put("token",token);
//        return returnMap;
//    }
    /**
     * 无异常 请求成功并无具体内容返回
     * @return 返回message
     */
    public ReturnMessage<Object> success() {
        return new ReturnMessage<Object>("000000","成功",null,null);
    }

    /**
     * 无异常 请求成功并有具体message内容和message内容返回
     * @param message 需返回的message内容
     * @param object 需返回的object内容
     * @return 返回ReturnMessage消息
     */
    public ReturnMessage<Object> successWithObjectAndMessage(String message,Object object) {
        return new ReturnMessage<Object>("000000","success",message,object);
    }

    /**
     * 有自定义错误异常信息
     * @param statusCode 错误码
     * @return 返回message
     */
    public ReturnMessage<Object> error(String statusCode) {
        ReturnInfo returnInfo = returnInfoMapper.selectReturnInfoByCode(statusCode);
        return new ReturnMessage<Object>(statusCode,returnInfo.getRespInfo(),null,null);
    }

}