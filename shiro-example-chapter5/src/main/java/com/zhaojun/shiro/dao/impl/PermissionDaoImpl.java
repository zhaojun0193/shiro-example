package com.zhaojun.shiro.dao.impl;

import com.zhaojun.shiro.dao.PermissionDao;
import com.zhaojun.shiro.entity.Permission;
import com.zhaojun.shiro.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhaojun0193
 * @create 2018/5/4
 */
public class PermissionDaoImpl implements PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    public Permission createPermission(final Permission permission) {
        final String sql = "INSERT INTO sys_permissions (permission,description,available) VALUES (?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, permission.getPermission());
                ps.setString(2, permission.getDescription());
                ps.setBoolean(3, permission.getAvailable());

                return ps;
            }
        }, keyHolder);

        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    public void deletePermission(Long permissionId) {
        final String sql = "DELETE FROM sys_permissions WHERE id = ?";
        jdbcTemplate.update(sql, permissionId);
    }
}
