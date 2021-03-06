package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.FridgeIngredientsDAO;
import com.siit.thebigproject.domain.FridgeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository
public class SQLFridgeIngredientsDAO extends SQLBaseDAO<FridgeIngredient> implements FridgeIngredientsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLFridgeIngredientsDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<FridgeIngredient> getAll() {
        return jdbcTemplate.query("select * from fridge_ingredients",
                new FridgeIngredientMapper());
    }

    @Override
    public FridgeIngredient getById(Long id) {
        return jdbcTemplate.queryForObject("select * from fridge_ingredients where id = ?",
                new FridgeIngredientMapper(), id);
    }

    @Override
    public boolean deleteByFridgeId(long fridgeId) {
        return jdbcTemplate.update("delete from fridge_ingredients where fridge_id = ?", fridgeId) > 0;
    }

    @Override
    public boolean deleteByIngredientId(long ingredientId) {
        return jdbcTemplate.update("delete from fridge_ingredients where ingredient_id = ?", ingredientId) > 0;
    }

    @Override
    public FridgeIngredient update(FridgeIngredient model) {
        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "update fridge_ingredients set fridge_id=?, ingredient_id = ? "
                    + "where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getFridgeId(),
                    model.getIngredientId(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "insert into fridge_ingredients (fridge_id, ingredient_id) "
                    + "values (?, ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getFridgeId(),
                    model.getIngredientId()
            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        }

        model.setId(newId);

        return model;
    }

    @Transactional
    @Override
    public boolean delete(FridgeIngredient model) {
        return jdbcTemplate.update("delete from fridge_ingredients where id = ?", model.getId()) > 0;
    }

    @Transactional
    @Override
    public boolean deleteByIds(long fridge_id, long ingredient_id) {
        Collection<FridgeIngredient> all = getAll();
        long id = -1;
        for (FridgeIngredient f : all) {
            if (f.getFridgeId() == fridge_id && f.getIngredientId() == ingredient_id) {
                id = f.getId();
            }
        }
        return jdbcTemplate.update("delete from fridge_ingredients where id = ?", id) > 0;
    }

    @Override
    public List<FridgeIngredient> getByFridgeId(long fridgeId) {
        return jdbcTemplate.query("select * from fridge_ingredients where fridge_id = ?",
                new FridgeIngredientMapper(), fridgeId);
    }

    private static class FridgeIngredientMapper implements RowMapper<FridgeIngredient> {

        @Override
        public FridgeIngredient mapRow(ResultSet rs, int arg1) throws SQLException {
            FridgeIngredient fridgeIngredient = new FridgeIngredient();
            fridgeIngredient.setId(rs.getLong("id"));
            fridgeIngredient.setFridgeId(rs.getLong("fridge_id"));
            fridgeIngredient.setIngredientId(rs.getLong("ingredient_id"));
            return fridgeIngredient;
        }
    }

}
