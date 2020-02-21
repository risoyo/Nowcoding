package com.nowcoder.community.util;

import com.nowcoder.community.entity.ReturnMessage;
import org.springframework.stereotype.Component;

@Component
public class ReturnMessageUtil {
    /**
     * 无异常 请求成功并有具体内容返回
     * @param object 需返回的内容
     * @return 返回message
     */
    public ReturnMessage<Object> sucess(Object object) {
        return new ReturnMessage<Object>(0,"sucess",object);
    }
    /**
     * 无异常 请求成功并无具体内容返回
     * @return 返回message
     */
    public ReturnMessage<Object> sucess() {
        return new ReturnMessage<Object>(0,"sucess",null);
    }
    /**
     * 有自定义错误异常信息
     * @param code 错误码
     * @param msg 需返回的内容
     * @return 返回message
     */
    public ReturnMessage<Object> error(Integer code,String msg) {
        return new ReturnMessage<Object>(code,msg,null);
    }
}
