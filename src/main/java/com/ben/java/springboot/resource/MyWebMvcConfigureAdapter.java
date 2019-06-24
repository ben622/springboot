package com.ben.java.springboot.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 扩展资源映射
 */
@Configuration
public class MyWebMvcConfigureAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mysource/**")//对外提供的访问路径
                .addResourceLocations("classpath:/external_source/"); //访问路径实际映射文件地址
    }
}
