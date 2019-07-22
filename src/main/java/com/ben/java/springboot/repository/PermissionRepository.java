package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PermissionRepository extends CrudRepository<Permission, Integer> {
    @Query(value = "SELECT DISTINCT\n" +
            "\tp.permissionId,\n" +
            "\tp.permissionName,\n" +
            "\tp.permissionUrl,\n" +
            "\tp.permissionCreateTime,\n" +
            "\tp.permissionRemark,\n" +
            "\n" +
            "IF (\n" +
            "\tp.permissionParentId IS NULL,\n" +
            "\t0,\n" +
            "\tp.permissionParentId\n" +
            ") AS permissionParentId\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tp.*\n" +
            "\t\tFROM\n" +
            "\t\t\tsys_user AS u\n" +
            "\t\tLEFT JOIN sys_user_permission AS up ON up.uid = u.uid\n" +
            "\t\tLEFT JOIN sys_permission_dict AS p ON p.permissionId = up.permissionId\n" +
            "\t\tWHERE\n" +
            "\t\t\tu.uid = :uid\n" +
            "\t\tUNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tp.*\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsys_user AS u\n" +
            "\t\t\tLEFT JOIN sys_role_permission AS rp ON rp.roleId = u.roleId\n" +
            "\t\t\tLEFT JOIN sys_permission_dict AS p ON p.permissionId = rp.permissionId\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tu.uid = :uid\n" +
            "\t) AS p where p.permissionId is not null", nativeQuery = true)
    List<Permission> findAllByUid(@Param("uid") int uid);
}
