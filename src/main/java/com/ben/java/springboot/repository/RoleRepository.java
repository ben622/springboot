package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.RoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRepository extends CrudRepository<RoleInfo,Integer> {
    Page<RoleInfo> findAll(Pageable pageable);
}
