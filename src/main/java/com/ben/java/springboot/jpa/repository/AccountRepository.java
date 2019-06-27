package com.ben.java.springboot.jpa.repository;

import com.ben.java.springboot.jpa.domain.UserInfo;
import org.springframework.data.repository.Repository;


public interface AccountRepository extends Repository<UserInfo, Integer> {
    UserInfo findUserInfoByUid(int uid);

}
