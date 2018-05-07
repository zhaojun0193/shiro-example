package com.zhaojun.shiro.service.impl;

import com.zhaojun.shiro.entity.User;
import com.zhaojun.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;
@Slf4j
public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void createUser() {
        User user = new User("zj","123");
        User newUser = userService.createUser(user);
        log.info(newUser.toString());
    }

    @Test
    public void changePassword() {
        userService.changePassword(14L,"1233");
    }

    @Test
    public void correlationRoles() {
        userService.correlationRoles(15L,3L);
    }

    @Test
    public void unCorrelationRoles() {
        userService.unCorrelationRoles(1L,1L);
    }

    @Test
    public void findByUsername() {
        log.info(userService.findByUsername("zj").toString());
    }

    @Test
    public void findRoles() {
        log.info(userService.findRoles("zj").toString());
    }

    @Test
    public void findPermissions() {
        log.info(userService.findPermissions("zj").toString());
    }
}