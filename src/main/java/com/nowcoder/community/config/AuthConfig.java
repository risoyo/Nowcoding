package com.nowcoder.community.config;

import com.nowcoder.community.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor initAuthInterceptor(){ //对拦截器进行初始化
        return new AuthInterceptor();
    }

    /**
     - /**： 匹配所有路径
     - /test/**：匹配 /test/ 下的所有路径
     - /secure/*：只匹配 /secure/user，不匹配 /secure/user/info

     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/test/**").excludePathPatterns("/login/**"); //为拦截器添加匹配规则
    }//addPathPatterns-指定拦截路径，excludePathPatterns-排除拦截路径


}

