package com.zhaojun.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaojun0193
 * @create 2018/5/2
 */
@Data
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String salt;

    private Boolean locked = Boolean.FALSE;

    public String getCredentialsSalt() {
        return username + salt;
    }


    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
