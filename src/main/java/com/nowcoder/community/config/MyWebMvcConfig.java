package com.nowcoder.community.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Value("${community.path.upload}")
    private String path;

    @Value("${community.path.avatarUpload}")
    private String avatarPath;

    /**
     * 配置资源访问
     * @param registry 默认
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源---图片url地址
        // 可添加多条，如下
        // http://localhost:8080/community/img/header/** 对应了avatarPath下所有的文件
        // http://localhost:8080/community/img/** 对应了path下所有的文件
        registry.addResourceHandler("/img/header/**").addResourceLocations("file:"+ avatarPath);
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+ path);
    }
}

