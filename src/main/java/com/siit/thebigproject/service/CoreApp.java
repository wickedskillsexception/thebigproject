package com.siit.thebigproject.service;

import com.siit.thebigproject.domain.*;

import java.util.*;

import static com.siit.thebigproject.domain.ApplicationConstants.MINIMUM_RECIPE_MATCH_PERCENT;

public class CoreApp {

    /// compute and send reply to servlet

    /// Adi todo: receive templates Fridge return List<Suggestion>

    public void recipeMatcher(Fridge userFridge, List<Recipe> recipeList) {


        List<FridgeIngredient> usersIngredients = userFridge.getIngredientList();

        Iterator<Recipe> recipeIterator = recipeList.listIterator();

        Map<Double, Recipe> matchedRecipes = new TreeMap<>(Collections.reverseOrder());

        while (recipeIterator.hasNext()) {
            double matchPercent = 0;


            Recipe currentRecipe = recipeIterator.next();
            List<RecipeIngredient> recipeIngredients = currentRecipe.getIngredientsList();

            double userIgredientListSize = usersIngredients.size();

            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                for (FridgeIngredient userIngredient : usersIngredients) {
                    if (recipeIngredient.equals(userIngredient)) {
                        matchPercent += 100 / userIgredientListSize * 0.7;

                        if (userIngredient.getQuantity() >= recipeIngredient.getQuantity()) {
                            matchPercent += 100 / userIgredientListSize * 0.3;
                        }
                    }
                }
            }
            if (matchPercent > MINIMUM_RECIPE_MATCH_PERCENT) {
                matchedRecipes.put(matchPercent, currentRecipe);

            }

        }

        for (Double key : matchedRecipes.keySet()) {
            System.out.println("Precent matched: + " + key + "%.\n" + "Recipe " + matchedRecipes.get(key).toString());
        }

    }


}
