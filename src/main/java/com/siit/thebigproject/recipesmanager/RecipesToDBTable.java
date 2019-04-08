package com.siit.thebigproject.recipesmanager;

import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;
import com.siit.thebigproject.recipesmanager.services.RecipeService;

import java.sql.SQLException;

public class RecipesToDBTable {

    public void insertRecipesToDB() throws TheBigProjectDBException, SQLException {

        ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();
        parseRecipeFromFileToObject.getRecipeListFromJSon();

        RecipeService recipeService = new RecipeService();
        recipeService.save(parseRecipeFromFileToObject.getRecipeList());
    }
}
