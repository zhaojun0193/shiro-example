package com.zhaojun.shiro.dao.impl;

import com.zhaojun.shiro.dao.RoleDao;
import com.zhaojun.shiro.entity.Role;
import com.zhaojun.shiro.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhaojun0193
 * @create 2018/5/7
 */
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    public Role createRole(final Role role) {
        final String sql = "INSERT INTO sys_roles (role,description,available) VALUES (?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, role.getRole());
                ps.setString(2, role.getDescription());
                ps.setBoolean(3, role.getAvailable());

                return ps;
            }
        }, keyHolder);

        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    public void deleteRole(Long roleId) {
        final String sql = "DELETE FROM sys_roles WHERE id = ?";
        jdbcTemplate.update(sql, roleId);
    }

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        final String sql = "INSERT INTO sys_roles_permissions (role_id,permission_id) VALUES (?,?)";
        for (Long permissionId : permissionIds) {
            jdbcTemplate.update(sql, roleId, permissionId);
        }
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        final String sql = "DELETE FROM sys_roles_permissions WHERE role_id = ? AND permission_id = ?";
        for (Long permissionId : permissionIds) {
            jdbcTemplate.update(sql, roleId, permissionId);
        }
    }
}
