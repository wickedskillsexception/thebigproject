package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.FridgesDAO;
import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.FridgeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class SQLFridgesDAO extends SQLBaseDAO<Fridge> implements FridgesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLFridgeIngredientDAO sqlFridgeIngredientDAO;

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
    public Fridge getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * from fridges WHERE id = ?;",
                new FridgeMapper(), id);

    }

   /* @Override
    public String getByIdWithUserDetails(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT f.id, u.username from fridges f INNER JOIN " +
                        "users u ON f.user_id = u.id WHERE f.id = ?;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();

                resultSet.next();
                String s = mapResultSetToString(resultSet);
                return s;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve fridge with specified ID: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
            return null;
        }
    }

    @Override
    public StringBuilder getAllWithUserDetails() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT f.id, u.username from fridges f INNER JOIN " +
                        "users u ON f.user_id = u.id ");
                ResultSet resultSet = selectPs.executeQuery();
                StringBuilder s = new StringBuilder();

                while(resultSet.next()) {
                    s.append(mapResultSetToString(resultSet));
                    s.append("\n");
                }
                return s;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Fridges: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
            return null;
        }
    }*/

    @Override
    public boolean delete(Fridge model) {
        return jdbcTemplate.update("DELETE from fridges WHERE id = ?;", model.getId()) > 0;
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




