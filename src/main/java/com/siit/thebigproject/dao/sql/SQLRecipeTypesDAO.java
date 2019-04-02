package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class SQLRecipeTypesDAO extends SQLBaseDAO<RecipeType> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SQLRecipeTypesDAO(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<RecipeType> getAll(){
        return jdbcTemplate.query("select * from recipeTypes", new RecipeTypeMapper());
    }

    @Override
    public RecipeType update(RecipeType model) {

        String sql = "";
        Long newId = null;
        if (model.getId() > 0) {
            sql = "UPDATE recipeTypes SET type = ? WHERE id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getType(),
                    model.getId()

            }, new RowMapper<Long>() {
                public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getLong(1);
                }
            });
        } else {
            sql = "INSERT INTO recipeTypes(type) values( ?) returning id";

            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getType()

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
    public RecipeType getById(Long id){
        return jdbcTemplate.queryForObject("select * from recipeTypes where id = ?", new RecipeTypeMapper(), id);
    }

    @Override
    public boolean delete(RecipeType recipeType){
        return jdbcTemplate.update("delete from recipeTypes where name = ?", recipeType.getId()) > 0;
    }

    private static class RecipeTypeMapper implements RowMapper<RecipeType> {

        @Override
        public RecipeType mapRow(ResultSet rs, int arg1) throws SQLException {
            RecipeType recipeType = new RecipeType();
            recipeType.setId(rs.getLong("id"));
            recipeType.setType(rs.getString("type"));
            return recipeType;
        }

    }


}
