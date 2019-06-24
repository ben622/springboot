package com.ben.java.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器
 */
@Configuration
public class SystemInterceptorConfigure  extends WebMvcConfigurerAdapter {
    private List<String> patterns = new ArrayList<String>(){{
        add("/toLogin");
        add("/login");
    }};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/*").excludePathPatterns(String.valueOf(patterns));
    }
}
