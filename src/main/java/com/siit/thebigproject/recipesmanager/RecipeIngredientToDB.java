package com.siit.thebigproject.recipesmanager;

import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;
import com.siit.thebigproject.recipesmanager.services.RecipeIngredientService;

import java.sql.SQLException;

public class RecipeIngredientToDB {

    public void insertRecipeIngredientToDB() throws TheBigProjectDBException, SQLException {

        ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();
        parseRecipeFromFileToObject.getRecipeListFromJSon();
        parseRecipeFromFileToObject.getAllIngredientsList();

        RecipeIngredientService recipeIngredientService = new RecipeIngredientService();
        recipeIngredientService.save(parseRecipeFromFileToObject.getRecipeList(), parseRecipeFromFileToObject.getAllIngredients());
    }
}
