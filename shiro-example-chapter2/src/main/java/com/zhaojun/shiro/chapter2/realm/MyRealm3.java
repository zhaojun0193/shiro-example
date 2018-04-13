package com.zhaojun.shiro.chapter2.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author zhaojun0193
 * @create 2018/4/13
 */
@Slf4j
public class MyRealm3 implements Realm {
    public String getName() {
        return "myRealm3";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        String password = new String((char[])authenticationToken.getCredentials());

        log.info("username:{},password:{}",username,password);

        if(!"zhang".equals(username)){
            log.info("用户名错误");
            throw new UnknownAccountException();
        }

        if(!"123".equals(password)){
            log.info("密码错误");
            throw new IncorrectCredentialsException();
        }
        //若身份认真成功，返回一个AuthenticationInfo实现
        username = "zhang@163.com";
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
