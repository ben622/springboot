package com.ben.java.springboot.domain;

import javax.persistence.*;


@Entity
@Table(name = "sys_log_user_login")
public class UserLoginLog {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int uid;
    @Column
    private String IP;
    @Column
    private String token;
    @Column
    private String loginTime;
    @Column
    private String loginDevice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginDevice() {
        return loginDevice;
    }

    public void setLoginDevice(String loginDevice) {
        this.loginDevice = loginDevice;
    }
}
