package com.siit.thebigproject.service;

import com.siit.thebigproject.domain.*;

import java.util.*;

import static com.siit.thebigproject.domain.ApplicationConstants.MAX_SUGGESTIONS;
import static com.siit.thebigproject.domain.ApplicationConstants.MINIMUM_RECIPE_MATCH_PERCENT;

public class CoreApp {

    /// compute and send reply to servlet

    /// Adi todo: receive templates Fridge return List<Suggestion>

    public Map<Double, Recipe> recipeMatcher(Fridge userFridge, List<Recipe> recipeList) {


        List<FridgeIngredient> usersIngredients = userFridge.getIngredientList();

        Iterator<Recipe> recipeIterator = recipeList.listIterator();

        Map<Double, Recipe> matchedRecipes = new TreeMap<>(Collections.reverseOrder());

        while (recipeIterator.hasNext()) {
            double matchPercent = 0;

            Recipe currentRecipe = recipeIterator.next();
            List<RecipeIngredient> recipeIngredients = currentRecipe.getIngredientsList();

            double userIngredientListSize = usersIngredients.size();
            double divider = (userIngredientListSize <= recipeIngredients.size()) ? userIngredientListSize : recipeIngredients.size();

            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                for (FridgeIngredient userIngredient : usersIngredients) {
                    if (recipeIngredient.equals(userIngredient)) {
                        matchPercent += 100 / divider; //* 0.7; how important the ingredients are to overall matching

//                        if (userIngredient.getQuantity() >= recipeIngredient.getQuantity()) {
//                            matchPercent += 100 / userIngredientListSize * 0.3;
//                        }
                    }
                }
            }
            if (matchPercent >= MINIMUM_RECIPE_MATCH_PERCENT) {
                matchedRecipes.put(matchPercent, currentRecipe);

            }

        }


//        for (Double key : matchedRecipes.keySet()) {
//            System.out.println("Precent matched: + " + key + "%.\n" + "Recipe " + matchedRecipes.get(key).toString());
//        }

         return matchedRecipes;
    }

    public List<Suggestion> createSuggestions(Map<Double, Recipe> matchedRecipes, Fridge userFridge){

        List<Suggestion> suggestions = new ArrayList<>();
        //limit maximum suggestions

        Map<Double, Recipe> bestMatches = matchedRecipes.entrySet().stream()
                .limit(MAX_SUGGESTIONS)
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        //check for missing ingredients

        bestMatches.entrySet().forEach(entry ->
                 {
                    Suggestion suggestion = new Suggestion();
                    suggestion.setMatchPercent(entry.getKey());

                    List<RecipeIngredient> recipeIngredients = entry.getValue().getIngredientsList();

                    for (RecipeIngredient ingredient : recipeIngredients){
                        for(FridgeIngredient fridgeIngredient : userFridge.getIngredientList()){

                        }

                    }


                }
        );





        return suggestions;

    }


}
