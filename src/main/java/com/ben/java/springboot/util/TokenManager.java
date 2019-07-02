package com.ben.java.springboot.util;

import com.ben.java.springboot.bean.TokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenManager {
    @Autowired
    private BeanFactory beanFactory;

    @Resource
    private RedisTemplate<String,TokenWrapper> redisTemplate;


    //token最大有效时间
    private final long TOKEN_MAX_ENABLE_TIMESTEMP = 1000 * 60;

    public TokenWrapper generateToken(){
        TokenWrapper tokenWrapper = (TokenWrapper) beanFactory.getApplicationContext().getBean("TokenWrapper");
        redisTemplate.opsForValue().set(tokenWrapper.getToken(), tokenWrapper);
        return tokenWrapper;
    }


    public TokenWrapper getTokenWrapperByToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }


}
