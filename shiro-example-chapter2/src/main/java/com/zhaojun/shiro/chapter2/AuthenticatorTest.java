package com.zhaojun.shiro.chapter2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.List;

/**
 * @author zhaojun0193
 * @create 2018/4/13
 */
@Slf4j
public class AuthenticatorTest {

    private void login(String configFile){
        //获取SecurityManager工厂，使用配置文件初始化工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //得到SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取Subject
        Subject subject = SecurityUtils.getSubject();

        //创建Token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        //登录
        subject.login(token);
    }

    @Test
    public void testAllSuccessfulStrategy(){
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合
        PrincipalCollection principals = subject.getPrincipals();
        List list = principals.asList();
        for(Object i : list){
            log.info(i.toString());
        }
    }

    @Test
    public void testAtLeastOneSuccessfulStrategy(){
        login("classpath:shiro-authenticator-least-one-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合
        PrincipalCollection principals = subject.getPrincipals();
        List list = principals.asList();
        for(Object i : list){
            log.info(i.toString());
        }
    }
}
