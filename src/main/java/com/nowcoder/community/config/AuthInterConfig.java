package com.nowcoder.community.config;

import com.nowcoder.community.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 创建一个拦截器类，对token信息进行拦截校验
 */
@Configuration
public class AuthInterConfig implements WebMvcConfigurer {

    // 必须先实例化AuthInterceptor，否则AuthInterceptor中的注入不会生效
    @Bean
    public AuthInterceptor initAuthInterceptor() { //对拦截器进行初始化
        return new AuthInterceptor();
    }

    /**
     * - /**： 匹配所有路径
     * - /test/**：匹配 /test/ 下的所有路径
     * - /secure/*：只匹配 /secure/user，不匹配 /secure/user/info
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**"); //为拦截器添加匹配规则
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg")

    }//addPathPatterns-指定拦截路径，excludePathPatterns-排除拦截路径
}

