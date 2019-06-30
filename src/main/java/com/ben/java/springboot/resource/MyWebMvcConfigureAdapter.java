package com.ben.java.springboot.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展资源映射
 */
@Configuration
public class MyWebMvcConfigureAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")//对外提供的访问路径
                .addResourceLocations("classpath:/web/"); //访问路径实际映射文件地址
    }
}
