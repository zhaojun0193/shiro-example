package com.zhaojun.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author zhaojun0193
 * @create 2018/5/7
 */
public class BaseTest {
    protected void login(String configPath,String username,String pasword){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configPath);
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,pasword);

        subject.login(token);
    }

    protected Subject subject(){
        return SecurityUtils.getSubject();
    }
}
