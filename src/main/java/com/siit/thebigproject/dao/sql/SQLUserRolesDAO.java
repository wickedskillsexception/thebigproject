package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLUserRolesDAO extends SQLBaseDAO<UserRole> implements UserRolesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLUserRolesDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserRole> getAll() {
        return jdbcTemplate.query("SELECT * from user_roles;",
                new SQLUserRolesDAO.UserRoleMapper());
    }

    @Override
    public UserRole update(UserRole userRole) {

        String sql = "";
        Long newId = null;
        if (userRole.getId() > 0) {
            sql = "UPDATE user_roles set user_id = ?, role_id = ? where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    userRole.getUser_id(),
                    userRole.getRole_id(),
                    userRole.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO user_roles(user_id, role_id) values (?, ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    userRole.getUser_id(),
                    userRole.getRole_id(),
                    userRole.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        }
        userRole.setId(newId);

        return userRole;
    }

    @Override
    public UserRole getById(Long id) {
        return jdbcTemplate.queryForObject("select * from user_roles where id = ?",

                new SQLUserRolesDAO.UserRoleMapper(), id);
    }

    @Override
    public boolean delete(UserRole userRole) {
        return jdbcTemplate.update("delete from user_roles where id = ?", userRole.getId()) > 0;
    }

    @Override
    public boolean deleteByUserId(long userId) {
        return jdbcTemplate.update("delete from user_roles where user_id = ?", userId) > 0;
    }

    private static class UserRoleMapper implements RowMapper<UserRole> {

        @Override
        public UserRole mapRow(ResultSet rs, int arg1) throws SQLException {
            UserRole userRole = new UserRole();
            userRole.setId(rs.getLong("id"));
            userRole.setUser_id(rs.getLong("user_id"));
            userRole.setRole_id(rs.getLong("role_id"));
            return userRole;
        }

    }

}
