package com.zhaojun.shiro.service;

import com.zhaojun.shiro.entity.Permission;

public interface PermissionService {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
