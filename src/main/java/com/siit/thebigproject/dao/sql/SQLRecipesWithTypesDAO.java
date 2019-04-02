package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.RecipeWithType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class SQLRecipesWithTypesDAO extends SQLBaseDAO<RecipeWithType> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLRecipesWithTypesDAO(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<RecipeWithType> getAll(){
        return jdbcTemplate.query("select * from recipesWithTypes", new RecipeWithTypeMapper());
    }

    @Override
    public RecipeWithType update(RecipeWithType model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE recipesWithTypes SET recipe_id = ?, type_id = ? WHERE id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getRecipeId(),
                    model.getTypeId(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO recipesWithTypes(recipe_id, type_id) values( ?, ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getRecipeId(),
                    model.getTypeId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
            model.setId(newId);

        }
        return model;
    }

    @Override
    public RecipeWithType getById(Long id){
        return jdbcTemplate.queryForObject("select * from recipesWithTypes where id = ?", new RecipeWithTypeMapper(), id);
    }

    @Override
    public boolean delete(RecipeWithType recipeWithType){
        return jdbcTemplate.update("delete from recipesWithTypes where id = ?", recipeWithType.getId()) > 0;
    }

    private static class RecipeWithTypeMapper implements RowMapper<RecipeWithType> {

        @Override
        public RecipeWithType mapRow(ResultSet rs, int arg1) throws SQLException {
            RecipeWithType recipeWithType = new RecipeWithType();
            recipeWithType.setId(rs.getLong("id"));
            recipeWithType.setRecipeId(rs.getLong("recipe_id"));
            recipeWithType.setTypeId(rs.getLong("type_id"));
            return recipeWithType;
        }

    }


}
