package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

// 主动的方式去配置bean
// 配置类，用于配置第三方jar包中的bean
@Configuration  // 通常配置类用此注解，表示该类为配置类
public class AlphaConfig {

    @Bean  //在引用第三方bean的类之前要加此注解,配置之后bean的名字就是下方的方法名
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    // 标明该方法返回的对象将被装配到容器之中
}
