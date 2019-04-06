package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.RecipeIngredientsDAO;
import com.siit.thebigproject.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SQLRecipeIngredientsDAO extends SQLBaseDAO<RecipeIngredient> implements RecipeIngredientsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLRecipeIngredientsDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public RecipeIngredient update(RecipeIngredient model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE recipe_ingredients SET recipe_id = ?, ingredient_id = ? WHERE id = ?";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getRecipeId(),
                    model.getIngredientId(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO recipe_ingredients(recipe_id, ingredient_id) values( ?, ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getRecipeId(),
                    model.getIngredientId(),

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
    public List<RecipeIngredient> getAll() {
        return jdbcTemplate.query("SELECT * from recipe_ingredients;", new RecipeIngredientMapper());
    }

    @Override
    public RecipeIngredient getById(Long id) {
        return jdbcTemplate.queryForObject("select * from recipe_ingredients where id = ?",

                new RecipeIngredientMapper(), id);
    }

    @Override
    public List<RecipeIngredient> getByRecipeId(long recipeId) {
        return jdbcTemplate.query("select * from recipe_ingredients where recipe_id = ?",
                new RecipeIngredientMapper(), recipeId);
    }

    @Override
    public boolean delete(RecipeIngredient ingredient) {
        return jdbcTemplate.update("delete from recipe_ingredients where id = ?",
                new RecipeIngredientMapper(), ingredient.getId()) > 0;

    }

    @Override
    public boolean deleteByRecipeId(long recipeId){
        return jdbcTemplate.update("delete from recipe_ingredients where id = ?",
                new RecipeIngredientMapper(), recipeId) > 0;

    }

    private static class RecipeIngredientMapper implements RowMapper<RecipeIngredient> {

        @Override
        public RecipeIngredient mapRow(ResultSet rs, int arg1) throws SQLException {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setId(rs.getLong("id"));
            recipeIngredient.setIngredientId(rs.getLong("ingredient_id"));
            recipeIngredient.setRecipeId(rs.getLong("recipe_id"));
            return recipeIngredient;
        }

    }
}
