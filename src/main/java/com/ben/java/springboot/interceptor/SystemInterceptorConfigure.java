package com.ben.java.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器
 */
@Configuration
public class SystemInterceptorConfigure  implements WebMvcConfigurer {
    private List<String> patterns = new ArrayList<String>(){{
        add("/toLogin");
        add("/login");
        add("/css/**");
        add("/js/**");
    }};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/*").excludePathPatterns(patterns);
    }
}
