package com.ben.java.springboot.account.service;

import com.ben.java.springboot.bean.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    List<UserInfo> findAll();
}
