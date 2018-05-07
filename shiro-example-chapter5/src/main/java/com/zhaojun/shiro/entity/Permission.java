package com.zhaojun.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaojun0193
 * @create 2018/5/2
 */
@Data
public class Permission implements Serializable{
    private Long id;

    /**
     * 权限标识 程序中判断使用,如"user:create"
     */
    private String permission;

    /**
     * 权限描述,UI界面显示使用
     */
    private String description;

    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission role = (Permission) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
