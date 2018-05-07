package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.dao.UserDao;
import com.zhaojun.shiro.dao.impl.UserDaoImpl;
import com.zhaojun.shiro.entity.User;
import com.zhaojun.shiro.service.PasswordHelper;
import com.zhaojun.shiro.service.UserService;

import java.util.Set;

/**
 * @author zhaojun0193
 * @create 2018/5/3
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    private PasswordHelper passwordHelper = new PasswordHelper();

    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.save(user);
    }

    public User get(Long userId) {
        return userDao.get(userId);
    }

    public void changePassword(Long userId, String newPassword) {
        User user = userDao.get(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    public void unCorrelationRoles(Long userId, Long... roleIds) {
        userDao.unCorrelationRoles(userId,roleIds);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
