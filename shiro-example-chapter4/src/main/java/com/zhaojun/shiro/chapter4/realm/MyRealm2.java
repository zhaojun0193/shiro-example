package com.zhaojun.shiro.chapter4.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author zhaojun0193
 * @create 2018/4/27
 */
public class MyRealm2 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名及salt1
        String username = "liu";
        //加密后的密码
        String password = "202cb962ac59075b964b07152d234b70";
        String salt2 = "202cb962ac59075b964b07152d234b70";
        //盐是用户名+随机数
        SimpleAuthenticationInfo ai =
                new SimpleAuthenticationInfo(username, password, getName());
        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));
        return ai;
    }
}
