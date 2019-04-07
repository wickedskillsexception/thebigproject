package com.siit.thebigproject.service.recipesManager;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientsToDBTable {

    @Autowired
    private ParseRecipeFromFileToObject parseRecipeFromFileToObject;

    @Autowired
    private IngredientService ingredientService;

    public void insertIngredientsToDB() throws ValidationException {

        parseRecipeFromFileToObject.getAllIngredientsList();
        parseRecipeFromFileToObject.printAllIngredients();
        for (Ingredient i : parseRecipeFromFileToObject.getAllIngredients()) {
            ingredientService.save(i);
        }


    }
}
