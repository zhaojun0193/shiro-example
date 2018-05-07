package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.dao.PermissionDao;
import com.zhaojun.shiro.dao.impl.PermissionDaoImpl;
import com.zhaojun.shiro.entity.Permission;
import com.zhaojun.shiro.service.PermissionService;

/**
 * @author zhaojun0193
 * @create 2018/5/4
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
