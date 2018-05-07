package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.entity.Role;
import com.zhaojun.shiro.service.RoleService;
import org.junit.Test;

public class RoleServiceImplTest {

    private RoleService roleService = new RoleServiceImpl();

    @Test
    public void createRole() {
        Role role = new Role();
        role.setRole("admin");
        role.setDescription("管理员");
        role.setAvailable(Boolean.TRUE);
        roleService.createRole(role);
    }

    @Test
    public void deleteRole() {
        roleService.deleteRole(1L);
    }

    @Test
    public void correlationPermissions() {
        roleService.correlationPermissions(3L,2L);
    }

    @Test
    public void uncorrelationPermissions() {
        roleService.uncorrelationPermissions(1L,1L);
    }
}