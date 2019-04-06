package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.BaseDAO;
import com.siit.thebigproject.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SQLIngredientsDAO extends SQLBaseDAO<Ingredient> implements BaseDAO<Ingredient> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLIngredientsDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Ingredient> getAll() {
        return jdbcTemplate.query("SELECT * from ingredients;",
                new IngredientMapper());
    }

    private Ingredient mapResultSetToIngredient(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        ingredient.setPictureUrl(resultSet.getString("picture"));

        return ingredient;
    }

    @Override
    public Ingredient update(Ingredient ingredient) {

        String sql = "";
        Long newId = null;
        if (ingredient.getId() > 0) {
            sql = "UPDATE ingredients set picture = ? where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    ingredient.getName(),
                    ingredient.getPictureUrl(),
                    ingredient.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO ingredients(name, unit, unitFactorTransformation) values (?, ?, ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    ingredient.getName(),
                    ingredient.getPictureUrl(),
                    ingredient.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        }
        ingredient.setId(newId);

        return ingredient;
    }

    @Override
    public Ingredient getById(Long id) {
        return jdbcTemplate.queryForObject("select * from ingredients where id = ?",

                new IngredientMapper(), id);
    }

    @Override
    public boolean delete(Ingredient ingredient) {
        return jdbcTemplate.update("delete from ingredients where id = ?", ingredient.getId()) > 0;
    }

    private static class IngredientMapper implements RowMapper<Ingredient> {

        @Override
        public Ingredient mapRow(ResultSet rs, int arg1) throws SQLException {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getLong("id"));
            ingredient.setName(rs.getString("name"));
            ingredient.setPictureUrl(rs.getString("picture"));
            return ingredient;
        }

    }

}
