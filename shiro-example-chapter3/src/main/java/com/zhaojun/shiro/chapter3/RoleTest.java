package com.zhaojun.shiro.chapter3;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author zhaojun0193
 * @create 2018/4/18
 */
@Slf4j
public class RoleTest extends BaseTest{


    @Test
    public void testHasRole(){
        String username = "wang";
        String password = "123";
        //登录
        login("classpath:shiro-role.ini",username,password);
        //判断是否拥有角色
        log.info(username + "是否拥有角色role1:"+SecurityUtils.getSubject().hasRole("role1"));
        log.info(username + "是否拥有角色role2:"+SecurityUtils.getSubject().hasRole("role2"));
        log.info(username + "是否拥有角色role1与role2:"+
                Arrays.toString(SecurityUtils.getSubject().hasRoles(Arrays.asList("role1","role2"))));

    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole(){
        String username = "wang";
        String password = "123";
        //登录
        login("classpath:shiro-role.ini",username,password);
        //断言拥有角色
        SecurityUtils.getSubject().checkRole("role2");

        SecurityUtils.getSubject().checkRoles("role1","role2");
    }

}
