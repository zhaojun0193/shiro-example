package com.zhaojun.shiro;

import com.zhaojun.shiro.BaseTest;
import org.junit.Test;

/**
 * @author zhaojun0193
 * @create 2018/5/7
 */
public class RoleAndPermissionTest extends BaseTest{
    @Test
    public void role(){
        login("classpath:shiro.ini","zj","123");
        boolean hasRole = subject().hasRole("admin");
        System.out.println(hasRole);
        subject().checkPermission("user:create");
    }
}
