package com.zhaojun.shiro.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author zhaojun0193
 * @create 2018/4/20
 */
public class BaseTest {

    protected void login(String configFile,String username, String password){
        //获取SecurityManager工厂，使用配置文件初始化工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //得到SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取Subject
        Subject subject = SecurityUtils.getSubject();

        //创建Token
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        //登录
        subject.login(token);
    }
}
