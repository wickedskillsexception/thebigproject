package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SQLRolesDAO extends SQLBaseDAO<Role> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLRolesDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Role> getAll() {
        return jdbcTemplate.query("SELECT * from roles;",
                new SQLRolesDAO.RoleMapper());
    }

    @Override
    public Role update(Role role) {

        String sql = "";
        Long newId = null;
        if (role.getId() > 0) {
            sql = "UPDATE roles set name = ? where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    role.getRoleName(),
                    role.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO roles(name) values (?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    role.getRoleName()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        }
        role.setId(newId);

        return role;
    }

    @Override
    public Role getById(Long id) {
        return jdbcTemplate.queryForObject("select * from roles where id = ?",

                new SQLRolesDAO.RoleMapper(), id);
    }

    @Override
    public boolean delete(Role role) {
        return jdbcTemplate.update("delete from roles where id = ?", role.getId()) > 0;
    }

    private static class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int arg1) throws SQLException {
            Role role = new Role();
            role.setId(rs.getLong("id"));
            role.setRoleName(rs.getString("name"));

            return role;
        }

    }

}