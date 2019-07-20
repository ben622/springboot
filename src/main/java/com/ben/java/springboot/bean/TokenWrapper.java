package com.ben.java.springboot.bean;

import com.ben.java.springboot.domain.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.UUID;

@Configuration
public class TokenWrapper implements Serializable {
    private String token;
    private long createTime;
    private UserInfo userInfo;

    public TokenWrapper() {
    }

    public TokenWrapper(long createTime) {
        this.token = UUID.randomUUID().toString().replaceAll("-", "");
        ;
        this.createTime = createTime;
    }

    @Bean("TokenWrapper")
    @Scope(scopeName = "prototype")
    public TokenWrapper getTokenWrapper() {
        return new TokenWrapper(System.currentTimeMillis());
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "TokenWrapper{" +
                "token='" + token + '\'' +
                ", createTime=" + createTime +
                ", userInfo=" + userInfo +
                '}';
    }

}
