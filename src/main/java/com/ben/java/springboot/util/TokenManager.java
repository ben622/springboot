package com.ben.java.springboot.util;

import com.ben.java.springboot.bean.TokenWrapper;
import com.ben.java.springboot.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class TokenManager {
    @Autowired
    private BeanFactory beanFactory;

    private ConcurrentHashMap<String,TokenWrapper> tokens = new ConcurrentHashMap<>();

    //token最大有效时间
    private final long TOKEN_MAX_ENABLE_TIMESTEMP = 1000 * 60;

    public TokenWrapper generateTokenByUser(@NotNull UserInfo userInfo){
        TokenWrapper tokenWrapper = beanFactory.getApplicationContext().getBean(TokenWrapper.class);
        tokenWrapper.setUserInfo(userInfo);
        tokens.put(tokenWrapper.getToken(), tokenWrapper);
        return tokenWrapper;
    }


    public TokenWrapper getTokenWrapperByToken(String token) {
        return tokens.get(token);
    }



}
