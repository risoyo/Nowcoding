package com.nowcoder.community.util;


import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefultExceptionHandler {
    @Autowired
    ReturnService returnService;

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnMessage<?> defultExcepitonHandler(BizException e) {
        return returnService.errorMessage(e.getErrorCode(),e.getErrorMessage());
    }
}