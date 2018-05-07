package com.zhaojun.shiro.dao.impl;

import com.zhaojun.shiro.dao.UserDao;
import com.zhaojun.shiro.entity.User;
import com.zhaojun.shiro.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhaojun0193
 * @create 2018/5/3
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    public User save(final User user) {
        final String sql = "INSERT INTO sys_users(username, password, salt, locked) VALUES (?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getSalt());
                ps.setBoolean(4, user.getLocked());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public User get(Long userId) {
        final String sql = "SELECT * FROM sys_users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public void update(User user) {
        final String sql = "UPDATE sys_users SET username=?, password=?, salt=?,locked=? WHERE id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        final String sql = "INSERT INTO sys_users_roles(user_id, role_id) VALUES(?,?)";

        for (Long roleId : roleIds) {
            if (!exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    public void unCorrelationRoles(Long userId, Long... roleIds) {
        final String sql = "DELETE FROM sys_users_roles WHERE user_id = ? AND role_id = ?";
        for (Long roleId : roleIds) {
            if (exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    public User findByUsername(String username) {
        final String sql = "SELECT * FROM sys_users WHERE username = ?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
        if (userList.size() != 0) {
            return userList.get(0);
        }
        return null;
    }

    public Set<String> findRoles(String username) {
        final String sql = "SELECT role FROM sys_roles AS r \n" +
                "LEFT JOIN sys_users_roles AS ur ON r.id = ur.role_id \n" +
                "LEFT JOIN sys_users AS u ON u.id = ur.user_id\n" +
                "WHERE u.username = ?";
        List<String> roles = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<String>(roles);
    }

    public Set<String> findPermissions(String username) {
        final String sql = "SELECT permission FROM sys_permissions AS p \n" +
                "LEFT JOIN sys_roles_permissions AS rp ON p.id = rp.permission_id\n" +
                "LEFT JOIN sys_roles AS r ON rp.role_id = r.id\n" +
                "LEFT JOIN sys_users_roles AS ur ON ur.role_id = r.id\n" +
                "LEFT JOIN sys_users AS u ON u.id = ur.user_id\n" +
                "WHERE u.username = ?";
        List<String> permissions = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<String>(permissions);
    }

    private boolean exists(Long userId, Long roleId) {
        final String sql = "SELECT count(1) FROM sys_users_roles WHERE user_id=? AND role_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
    }
}
