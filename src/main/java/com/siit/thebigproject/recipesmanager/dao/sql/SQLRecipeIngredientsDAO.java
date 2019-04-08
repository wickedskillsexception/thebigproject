package com.siit.thebigproject.recipesmanager.dao.sql;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeIngredient;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.*;

public class SQLRecipeIngredientsDAO {

    private TheBigProjectDB db;

    public SQLRecipeIngredientsDAO(TheBigProjectDB db) {
        this.db = db;
    }

    public void add(RecipeIngredient recipeIngredient) throws TheBigProjectDBException, SQLException {
        try (Connection connection = db.connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO recipe_ingredients(recipe_id, ingredient_id) values(?, ?)");
            statement.setLong(1, recipeIngredient.getRecipeId());
            statement.setLong(2, recipeIngredient.getIngredientId());
            statement.executeUpdate();

        }
    }
}
