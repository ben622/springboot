package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * sys_user
 */
public interface UserRepository extends CrudRepository<UserInfo,Integer> {
    UserInfo findUserInfoByUserIdAndPassword(String userId, String password);
    UserInfo findUserInfoByMobileAndPassword(String mobile, String password);
    Page<UserInfo> findAll(Pageable pageable);
}
