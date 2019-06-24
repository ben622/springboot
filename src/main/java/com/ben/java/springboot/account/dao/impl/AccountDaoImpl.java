package com.ben.java.springboot.account.dao.impl;

import com.ben.java.springboot.account.dao.AccountDao;
import com.ben.java.springboot.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<UserInfo> findAll() {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        jdbcTemplate.query("select * from tb_user", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                UserInfo userInfo = new UserInfo();
                userInfo.setUid(resultSet.getInt("uid"));
                userInfo.setId(resultSet.getInt("id"));
                userInfo.setNickname(resultSet.getString("nickname"));
                userInfo.setPassword(resultSet.getString("password"));
                userInfo.setAge(resultSet.getInt("age"));
                userInfo.setMobile(resultSet.getString("mobile"));
                userInfo.setGender_id(resultSet.getInt("gender_id"));
                userInfo.setCreate_time(resultSet.getString("create_time"));
                userInfo.setLast_time(resultSet.getString("last_time"));
                userInfos.add(userInfo);
            }
        });
        return userInfos;
    }
}
