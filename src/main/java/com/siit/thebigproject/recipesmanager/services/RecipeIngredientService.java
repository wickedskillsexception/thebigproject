package com.siit.thebigproject.recipesmanager.services;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeIngredient;
import com.siit.thebigproject.recipesmanager.dao.sql.SQLRecipeIngredientsDAO;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.SQLException;
import java.util.List;

public class RecipeIngredientService {

    private SQLRecipeIngredientsDAO sqlRecipeIngredientsDAO;
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private TheBigProjectDB db;
    private int counter = 1;

    public void save(List<Recipe> recipeList, List<Ingredient> allIngredients) throws TheBigProjectDBException, SQLException {

        recipeService = new RecipeService();
        ingredientService = new IngredientService();
        db = new TheBigProjectDB();
        sqlRecipeIngredientsDAO = new SQLRecipeIngredientsDAO(db);


        for (Recipe r : recipeList) {

            long recipeID = recipeService.getRecipeIDByName(r);

            for (RecipeIngredient ri : r.getIngredientsList()) {
                for (Ingredient i : allIngredients) {
                    if (i.getId() == ri.getIngredientId()) {
                        long ingredientID = ingredientService.getIngredientIDByName(i);
                        sqlRecipeIngredientsDAO.add(new RecipeIngredient(recipeID, ingredientID));
                        System.out.println(counter++);
                        break;
                    }
                }
            }
        }
    }
}
