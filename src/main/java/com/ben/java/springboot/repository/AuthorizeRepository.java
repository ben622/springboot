package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.AuthorizeInfo;


public interface AuthorizeRepository extends CrudRepository<AuthorizeInfo, Integer> {

    AuthorizeInfo findAuthorizeInfoByAccid(String accid);

}
