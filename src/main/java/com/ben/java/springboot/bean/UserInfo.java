package com.ben.java.springboot.bean;


public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private int age;
    private UserVipInfo userVipInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserVipInfo getUserVipInfo() {
        return userVipInfo;
    }

    public void setUserVipInfo(UserVipInfo userVipInfo) {
        this.userVipInfo = userVipInfo;
    }
}
