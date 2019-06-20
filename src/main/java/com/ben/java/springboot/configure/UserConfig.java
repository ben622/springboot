package com.ben.java.springboot.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties({UserConfig.class})
@ConfigurationProperties(prefix = "com.bean.java")
@PropertySource("classpath:application.properties")
public class UserConfig {
    private String email;
    private String home;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "email='" + email + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
