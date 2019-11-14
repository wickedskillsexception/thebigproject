package com.siit.thebigproject.recipesmanager.dao.sql;

import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRecipesDAO {

    private TheBigProjectDB db;

    public SQLRecipesDAO(TheBigProjectDB db) {
        this.db = db;
    }

    public void add(Recipe recipe) throws TheBigProjectDBException, SQLException {
        try (Connection connection = db.connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO recipes(name, preparation, preparation_time, recipe_types, image, smart_points) values(?, ?, ?, ?, ?, ?)");
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getPreparation());
            statement.setInt(3, recipe.getPreparationTime());
            statement.setString(4, recipe.getRecipeTypes());
            statement.setString(5, recipe.getImage());
            statement.setInt(6, recipe.getSmartPoints());
            statement.executeUpdate();

        }
    }

    public Recipe getByRecipeName(Recipe  recipe) throws TheBigProjectDBException, SQLException {
        try (Connection conn = db.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * from recipes WHERE name = ? ;");
            statement.setString(1, recipe.getName());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return mapResultSetToRecipe(resultSet);
            } else {
                return null;
            }
        }
    }

    public Recipe mapResultSetToRecipe(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setPreparation(resultSet.getString("preparation"));
        recipe.setPreparationTime(resultSet.getInt("preparation_time"));
        recipe.setRecipeTypes(resultSet.getString("recipe_types"));
        recipe.setImage(resultSet.getString("image"));
        recipe.setSmartPoints(resultSet.getInt("smart_points"));

        return recipe;
    }
}
