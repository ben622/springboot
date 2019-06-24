package com.ben.java.springboot.account.service.impl;

import com.ben.java.springboot.account.dao.AccountDao;
import com.ben.java.springboot.account.service.AccountService;
import com.ben.java.springboot.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public List<UserInfo> findAll() {
        return accountDao.findAll();
    }
}
