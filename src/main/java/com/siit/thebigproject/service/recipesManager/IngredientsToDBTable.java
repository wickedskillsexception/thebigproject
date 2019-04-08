package com.siit.thebigproject.service.recipesManager;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientsToDBTable {

    public void insertIngredientsToDB() throws ValidationException {

        ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();
        IngredientService ingredientService = new IngredientService();

        parseRecipeFromFileToObject.getAllIngredientsList();
        parseRecipeFromFileToObject.printAllIngredients();
        for (Ingredient i : parseRecipeFromFileToObject.getAllIngredients()) {
            ingredientService.save(i);
        }


    }
}
