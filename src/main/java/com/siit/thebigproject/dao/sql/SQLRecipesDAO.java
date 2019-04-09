package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.RecipesDAO;
import com.siit.thebigproject.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class SQLRecipesDAO extends SQLBaseDAO<Recipe> implements RecipesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLRecipesDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private SQLRecipeIngredientsDAO sqlRecipeIngredientsDAO;

    @Override
    public Recipe getById(Long id){
        Recipe recipe = jdbcTemplate.queryForObject("select * from recipes where id = ?",
                new RecipeMapper(), id);
        List recipeIngredients = sqlRecipeIngredientsDAO.getByRecipeId(id);
        recipe.setIngredientsList(recipeIngredients);
        return recipe;
    }

    @Override
    public List<Recipe> getAll(){
        return jdbcTemplate.query("select * from recipes", new RecipeMapper());
    }

    @Override
    public Recipe update(Recipe model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE recipes SET name = ?, preparation = ?, preparation_time = ?, image = ?, smartPoints = ?, recipe_types  " +
                    "WHERE id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getName(),
                    model.getPreparation(),
                    model.getPreparationTime(),
                    model.getImage(),
                    model.getSmartPoints(),
                    model.getRecipeTypes(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO recipes(name, preparation, preparation_time, " +
                    " image, smart_points, recipe_types) values( ?, ?, ?, ?, ?, ?) returning id;";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getName(),
                    model.getPreparation(),
                    model.getPreparationTime(),
                    model.getImage(),
                    model.getSmartPoints(),
                    model.getRecipeTypes()

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
    public boolean deleteById(long recipeId){
        sqlRecipeIngredientsDAO.deleteByRecipeId(recipeId);
        return jdbcTemplate.update("delete from recipes where id = ?", recipeId) > 0;
    }

    @Override
    public boolean delete(Recipe recipe){
        sqlRecipeIngredientsDAO.deleteByRecipeId(recipe.getId());
        return jdbcTemplate.update("delete from recipes where id = ?", recipe.getId()) > 0;
    }

    private static class RecipeMapper implements RowMapper<Recipe> {

        @Override
        public Recipe mapRow(ResultSet rs, int arg1) throws SQLException {
            Recipe recipe = new Recipe();
            recipe.setId(rs.getInt("id"));
            recipe.setName(rs.getString("name"));
            recipe.setPreparation(rs.getString("preparation"));
            recipe.setPreparationTime(rs.getInt("preparation_time"));
            recipe.setImage(rs.getString("image"));
            recipe.setSmartPoints(rs.getInt("smart_points"));
            recipe.setRecipeTypes(rs.getString("recipe_types"));
            return recipe;
        }

    }

}
