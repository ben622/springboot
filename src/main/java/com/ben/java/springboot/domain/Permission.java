package com.ben.java.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sys_permission_dict")
@Entity
public class Permission implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;
    @Column
    private String permissionName;
    @Column
    private String permissionUrl;
    @Column
    private String permissionRemark;
    @Column
    private String permissionCreateTime;
    @Column
    private int permissionParentId;


    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
    }

    public String getPermissionCreateTime() {
        return permissionCreateTime;
    }

    public void setPermissionCreateTime(String permissionCreateTime) {
        this.permissionCreateTime = permissionCreateTime;
    }

    public int getPermissionParentId() {
        return permissionParentId;
    }

    public void setPermissionParentId(int permissionParentId) {
        this.permissionParentId = permissionParentId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", permissionRemark='" + permissionRemark + '\'' +
                ", permissionCreateTime='" + permissionCreateTime + '\'' +
                ", permissionParentId=" + permissionParentId +
                '}';
    }
}
