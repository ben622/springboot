package com.ben.java.springboot.account.dao;

import com.ben.java.springboot.bean.UserInfo;

import java.util.List;

public interface AccountDao {
    List<UserInfo> findAll();

}
