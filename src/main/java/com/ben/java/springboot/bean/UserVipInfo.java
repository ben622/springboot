package com.ben.java.springboot.bean;

import org.springframework.context.annotation.Configuration;

public class UserVipInfo {

    private int vipLevel;
    private String vipPermission;

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getVipPermission() {
        return vipPermission;
    }

    public void setVipPermission(String vipPermission) {
        this.vipPermission = vipPermission;
    }
}
