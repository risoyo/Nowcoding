package com.nowcoder.community.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JsonUtils {

    public String getJSONString(int code, String message, List<Map<String, Object>> object) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
//        json.put("data", json.toJSONString(object));
//        JSONArray jsonReturn = JSONArray.parseArray(JSONObject.toJSONString(object));
        json.put("data", json.toJSONString(object));
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(object));
        for (Map<String, Object> map : object) {
            for (Map.Entry<String, Object> m : map.entrySet()) {
                System.out.print(m.getKey() + "    ");
                System.out.println(m.getValue());
            }
        }
        return json.toJSONString();
    }

    public String getJSONString(int code, String message) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        return json.toJSONString();
    }

    public String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }
}
