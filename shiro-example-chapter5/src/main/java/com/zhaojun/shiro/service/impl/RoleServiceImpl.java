package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.dao.RoleDao;
import com.zhaojun.shiro.dao.impl.RoleDaoImpl;
import com.zhaojun.shiro.entity.Role;
import com.zhaojun.shiro.service.RoleService;

/**
 * @author zhaojun0193
 * @create 2018/5/7
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId,permissionIds);
    }
}
