package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.UserInfo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * sys_user
 */
public interface UserRepository extends CrudRepository<UserInfo,Integer> {
    UserInfo findUserInfoByUidOrMobileAndPassword(int uid, String mobile, String password);
}
