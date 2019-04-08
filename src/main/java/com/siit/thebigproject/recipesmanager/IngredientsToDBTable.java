package com.siit.thebigproject.recipesmanager;

import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;
import com.siit.thebigproject.recipesmanager.services.IngredientService;

import java.sql.SQLException;

public class IngredientsToDBTable {

    public void insertIngredientsToDB() throws TheBigProjectDBException, SQLException {

        ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();
        parseRecipeFromFileToObject.getAllIngredientsList();
        IngredientService ingredientService = new IngredientService();
        ingredientService.save(parseRecipeFromFileToObject.getAllIngredients());
    }
}
