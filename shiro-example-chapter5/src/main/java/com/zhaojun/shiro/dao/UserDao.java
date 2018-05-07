package com.zhaojun.shiro.dao;

import com.zhaojun.shiro.entity.User;

import java.util.Set;

/**
 * @author zhaojun0193
 * @create 2018/5/3
 */
public interface UserDao {
    /**
     * 创建账户
     */
    User save(User user);


    /**
     * 根据id查询
     */
    User get(Long userId);

    /**
     * 修改user
     */
    void update(User user);

    /**
     * 添加用户-角色关系
     */
    public void correlationRoles(Long userId, Long... roleIds);

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
