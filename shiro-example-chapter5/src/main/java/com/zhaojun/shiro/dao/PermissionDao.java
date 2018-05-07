package com.zhaojun.shiro.dao;

import com.zhaojun.shiro.entity.Permission;

/**
 * @author zhaojun0193
 * @create 2018/5/4
 */
public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
