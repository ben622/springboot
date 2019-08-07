package com.ben.java.springboot.util;

import com.ben.java.springboot.bean.TokenWrapper;
import com.ben.java.springboot.domain.UserInfo;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class TokenManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BeanFactory beanFactory;

    @Deprecated
    @Resource
    private RedisTemplate<String, TokenWrapper> redisTemplate;

    @Autowired
    private JedisTemplate jedisTemplate;


    public TokenWrapper generateToken(UserInfo userInfo) {
        logger.info("================================================");
        TokenWrapper tokenWrapper = (TokenWrapper) beanFactory.getApplicationContext().getBean("TokenWrapper");
        tokenWrapper.setUserInfo(userInfo);
        logger.info("=");
        logger.info("=  " + tokenWrapper.getToken());
        jedisTemplate.set(tokenWrapper.getToken(), tokenWrapper);
        logger.info("=  " + (SerializationUtils.deserialize(jedisTemplate.get(tokenWrapper.getToken().getBytes()))).toString());
        logger.info("=");
        logger.info("================================================");
        return tokenWrapper;
    }

    /**
     * TokenWrapper
     * @param token
     * @return
     */
    public TokenWrapper getTokenWrapperByToken(String token) {
        byte[] bytes = jedisTemplate.get(token.getBytes());
        return bytes == null ? null : SerializationUtils.deserialize(bytes);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public TokenWrapper refreshTokenWrapperByToken(String token) {
        byte[] bytes = jedisTemplate.get(token.getBytes());
        if (bytes == null) {
            return null;
        }
        TokenWrapper tokenWrapper = SerializationUtils.deserialize(bytes);
        tokenWrapper.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        jedisTemplate.set(tokenWrapper.getToken(), tokenWrapper);

        //remove origin token
        jedisTemplate.del(token);

        return tokenWrapper;

    }





}
