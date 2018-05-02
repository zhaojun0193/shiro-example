package com.zhaojun.shiro.chapter4;

import org.junit.Test;

/**
 * @author zhaojun0193
 * @create 2018/4/26
 */
public class PasswordTest extends BaseTest {

    @Test
    public void testPasswordServiceWithMyRealm(){
        login("classpath:shiro-passwordservice.ini","zhang","123");
    }

    @Test
    public void testPasswordServiceWithJdbcRealm(){
        login("classpath:shiro-jdbc-passwordservice.ini","wu","123");
    }
}
