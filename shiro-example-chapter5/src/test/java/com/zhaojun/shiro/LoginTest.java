package com.zhaojun.shiro;

import org.junit.Test;

/**
 * @author zhaojun0193
 * @create 2018/5/4
 */
public class LoginTest extends BaseTest{

    @Test
    public void loginTest(){
        login("classpath:shiro.ini","zj","123");
    }
}
