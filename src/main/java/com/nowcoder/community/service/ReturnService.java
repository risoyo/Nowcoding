package com.nowcoder.community.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReturnService {

    public Map<String, Object> returnMessage(int statusCode, String returnMessage) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("status", statusCode);
        returnMap.put("reason", returnMessage);
        return returnMap;
    }
}