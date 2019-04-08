package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.UsersDAO;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class SQLUsersDAO extends SQLBaseDAO<User> implements UsersDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLFridgesDAO sqlFridgesDAO;

    @Autowired
    private SQLUserRolesDAO sqlUserRolesDAO;

    public SQLUsersDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<User> getAll(){
        return jdbcTemplate.query("select * from users", new UserMapper());
    }

    @Override
    public User update(User model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE users SET full_name = ?, username = ?," +
                    "password = ?, email = ? WHERE id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getFullName(),
                    model.getUsername(),
                    model.getPassword(),
                    model.getEmail(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO users(full_name, username, password, email, active) values(?, ?, ?, ?, true) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getFullName(),
                    model.getUsername(),
                    model.getPassword(),
                    model.getEmail(),

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
            model.setId(newId);
            Fridge fridge = new Fridge();
            fridge.setUserId(model.getId());
            sqlFridgesDAO.update(fridge);
        }


        return model;
    }

    @Override
    public User getById(Long id){
        return jdbcTemplate.queryForObject("select * from users where id = ?", new UserMapper(), id);
    }

    @Override
    public boolean delete(User user){
        sqlFridgesDAO.deleteByUserId(user.getId());
        sqlUserRolesDAO.deleteByUserId(user.getId());
        return jdbcTemplate.update("delete from users where username = ?", user.getId()) > 0;
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            return user;
        }

    }
}

