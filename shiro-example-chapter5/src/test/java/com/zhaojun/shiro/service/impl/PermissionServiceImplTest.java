package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.dao.PermissionDao;
import com.zhaojun.shiro.dao.impl.PermissionDaoImpl;
import com.zhaojun.shiro.entity.Permission;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermissionServiceImplTest {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Test
    public void createPermission() {
        Permission permission = new Permission();
        permission.setPermission("user:create");
        permission.setAvailable(Boolean.TRUE);
        permission.setDescription("创建用户权限");
        permissionDao.createPermission(permission);
    }

    @Test
    public void deletePermission() {
        permissionDao.deletePermission(3L);
    }
}