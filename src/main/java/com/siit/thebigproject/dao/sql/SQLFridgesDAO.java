package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.FridgesDAO;
import com.siit.thebigproject.domain.Fridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;

@Repository
public class SQLFridgesDAO extends SQLBaseDAO<Fridge> implements FridgesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLFridgeIngredientsDAO sqlFridgeIngredientsDAO;


    @Override
    public Fridge getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * from fridges WHERE id = ?;",
                new FridgeMapper(), id);
    }

    @Override
    public Fridge getByUserId(Long id) {
        return jdbcTemplate.queryForObject("SELECT * from fridges WHERE user_id = ?;",
                new FridgeMapper(), id);
    }

    @Override
    public Collection<Fridge> getAll() {
        return jdbcTemplate.query("select * from fridges",
                new FridgeMapper());
    }

    @Override
    public Fridge update(Fridge model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE fridges SET user_id = ? WHERE id = ?\" returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getUserId(),
                    model.getId(),
            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO fridges(user_id) values( ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getUserId(),

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        }
        model.setId(newId);

        return model;
    }

    @Override
    public boolean delete(Fridge model) {
        return jdbcTemplate.update("DELETE from fridges WHERE id = ?;", model.getId()) > 0;
    }

    @Override
    public boolean deleteByUserId(long id) {
        return jdbcTemplate.update("delete from fridges where user_id = ?", id) > 0;
    }

    private static class FridgeMapper implements RowMapper<Fridge> {

        @Override
        public Fridge mapRow(ResultSet rs, int arg1) throws SQLException {
            Fridge fridge = new Fridge();
            fridge.setId(rs.getLong("id"));
            fridge.setUserId(rs.getLong("user_id"));
            return fridge;
        }
    }

}




