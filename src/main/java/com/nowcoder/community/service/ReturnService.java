package com.nowcoder.community.service;

import com.nowcoder.community.dao.ReturnInfoMapper;
import com.nowcoder.community.entity.ReturnInfo;
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

}