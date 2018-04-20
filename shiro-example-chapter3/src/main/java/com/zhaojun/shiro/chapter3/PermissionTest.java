package com.zhaojun.shiro.chapter3;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author zhaojun0193
 * @create 2018/4/18
 */
@Slf4j
public class PermissionTest extends BaseTest{

    @Test
    public void testPermission(){
        String username = "zhang";
        String password = "123";
        login("classpath:shiro-permission.ini",username,password);
        //判断是否拥有权限
        log.info(username + "是否拥有user:create权限："+SecurityUtils.getSubject().isPermitted("user:create"));
        log.info(username+"："+SecurityUtils.getSubject().isPermittedAll("user:create","user:update"));
        log.info(username + "是否拥有user:delete权限："+SecurityUtils.getSubject().isPermitted("user:delete"));
    }


    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        //断言拥有权限：user:create
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject.checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject.checkPermissions("user:view");
    }


    @Test
    public void testWildcardPermission1(){
        login("classpath:shiro-permission.ini","zhao","666");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("system:user:update","system:user:delete");
    }

    @Test
    public void testWildcardPermission2(){
        login("classpath:shiro-permission.ini","deng","520");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("system:user:update","system:user:delete");

        subject.checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission3(){
        login("classpath:shiro-permission.ini","li","123");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("system:user:create,update,delete,view");
        subject.checkPermissions("system:user:create","system:user:update","system:user:view","system:user:delete");
        subject.checkPermissions("system:user:*");
    }

    @Test
    public void testWildcardPermission4(){
        login("classpath:shiro-permission.ini","liu","123");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("user:view");
        subject.checkPermissions("product:view");
    }

    @Test
    public void testWildcardPermission5(){
        login("classpath:shiro-permission.ini","luo","123");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("user:view:1");
        subject.checkPermissions("user:update,delete:1");
        subject.checkPermissions("user:update:1","user:delete:1");
        subject.checkPermissions("user:auth:1","user:auth:2");
    }

    @Test
    public void testWildcardPermission(){
        login("classpath:shiro-permission.ini","liang","123");
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermissions("menu:view:1");

        subject.checkPermission(new WildcardPermission("menu:view:1"));
    }

}
