package com.zhaojun.shiro.service;

import com.zhaojun.shiro.entity.User;

import java.util.Set;

public interface UserService {
    /**
     * 创建账户
     */
    User createUser(User user);


    User get(Long userId);

    /**
     * 修改密码
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     */
    void unCorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     */
    Set<String> findPermissions(String username);
}
