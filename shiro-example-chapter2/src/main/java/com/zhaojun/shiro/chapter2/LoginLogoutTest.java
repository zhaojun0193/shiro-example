package com.zhaojun.shiro.chapter2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhaojun0193
 * @create 2018/4/13
 */
@Slf4j
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){
        //1. 获取SecurityManagerFactory，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2. 得到SecurityManager实例，并绑定到SecurityUtils上
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang","123");

        try{
            //4. 登录，即验证身份
            subject.login(token);
            log.info("用户登录成功");
        }catch (AuthenticationException e){
            log.error("身份验证失败:{}",e.getLocalizedMessage());
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        //6. 退出
        subject.logout();
    }

    @Test
    public void testCustomRealm(){
        //1. 获取SecurityManagerFactory，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2. 得到SecurityManager实例，并绑定到SecurityUtils上
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //4. 登录，即验证身份
            subject.login(token);
            log.info("用户登录成功");
        }catch (AuthenticationException e){
            log.error("身份验证失败:{}",e.getLocalizedMessage());
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        //6. 退出
        subject.logout();
    }

    @Test
    public void testCustomMultiRealm(){
        //1. 获取SecurityManagerFactory，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        //2. 得到SecurityManager实例，并绑定到SecurityUtils上
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang","123");

        try{
            //4. 登录，即验证身份
            subject.login(token);
            log.info("用户登录成功");
        }catch (AuthenticationException e){
            log.error("身份验证失败:{}",e.getLocalizedMessage());
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        //6. 退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm(){
        //1. 获取SecurityManagerFactory，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        //2. 得到SecurityManager实例，并绑定到SecurityUtils上
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //4. 登录，即验证身份
            subject.login(token);
            log.info("用户登录成功");
        }catch (AuthenticationException e){
            log.error("身份验证失败:{}",e.getLocalizedMessage());
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        //6. 退出
        subject.logout();
    }
}
