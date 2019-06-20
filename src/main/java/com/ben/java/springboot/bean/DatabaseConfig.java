package com.ben.java.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties({DatabaseConfig.class})
@ConfigurationProperties(prefix = "com.dbconfig")
@PropertySource("classpath:database.properties")
public class DatabaseConfig {
    private String dbname;
    private String dbpwd;
    private String uuid;
    private int port;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbpwd() {
        return dbpwd;
    }

    public void setDbpwd(String dbpwd) {
        this.dbpwd = dbpwd;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "dbname='" + dbname + '\'' +
                ", dbpwd='" + dbpwd + '\'' +
                ", uuid='" + uuid + '\'' +
                ", port=" + port +
                '}';
    }
}
