package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf()) //将api的元信息设置为包含在json resourcelisting响应中
                //.host("127.0.0.1:8080") //设置ip和端口，或者域名
                .select()  //启动用于api选择的生成器
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.any())//指定controller路径
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInf() {

        return new ApiInfoBuilder()
                .title("Nowcoding")//文档标题
                .description("一个小小的全栈论坛项目")//文档描述
                .version("1.0")//版本号
                //.license("")//更新此API的许可证信息
                //.licenseUrl("")//更新此API的许可证Url
                //.termsOfServiceUrl("")//更新服务条款URL
                .build();

    }


    /**
     * 静态资源配置(默认)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");// 静态资源路径
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
