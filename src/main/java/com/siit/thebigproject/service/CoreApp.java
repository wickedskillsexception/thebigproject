package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLIngredientsDAO;
import com.siit.thebigproject.domain.*;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.siit.thebigproject.domain.ApplicationConstants.MAX_SUGGESTIONS;
import static com.siit.thebigproject.domain.ApplicationConstants.MINIMUM_RECIPE_MATCH_PERCENT;

@Service
public class CoreApp {
    @Autowired
    SQLIngredientsDAO sqlIngredientsDAO;

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
                    if (recipeIngredient.getIngredientId() == userIngredient.getIngredientId()) {
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

    public List<Suggestion> createSuggestions(Map<Double, Recipe> matchedRecipes, Fridge userFridge) {


        List<Suggestion> suggestions = new ArrayList<>();
        //limit maximum suggestions

        if (matchedRecipes == null) {
            return null;
        }

        Map<Double, Recipe> bestMatches = matchedRecipes.entrySet().stream()
                .limit(MAX_SUGGESTIONS)
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        //check for missing ingredients

        bestMatches.entrySet().forEach(entry ->
                {
                    Suggestion suggestion = new Suggestion();
                    suggestion.setMatchPercent(entry.getKey());
                    suggestion.setRecipe(entry.getValue());

                    List<RecipeIngredient> recipeIngredients = entry.getValue().getIngredientsList();
                    if (suggestion.getMatchPercent() >= 99.9) {
                        suggestion.setHasAllIngredients(true);
                        suggestion.setMissingIngredients(null);
                        suggestions.add(suggestion);
                    } else {

                        suggestion.setHasAllIngredients(false);

                        ArrayList<Long> userIngredientsIds = new ArrayList<>();
                        for (FridgeIngredient ingredient : userFridge.getIngredientList()) {
                            userIngredientsIds.add(ingredient.getIngredientId());
                        }

                        ArrayList<Long> recipeIngredientsIds = new ArrayList<>();
                        for (RecipeIngredient recipeIngredient : recipeIngredients) {
                            recipeIngredientsIds.add(recipeIngredient.getIngredientId());
                        }

                        List<Long> missinIngredientsIds = ListUtils.subtract(recipeIngredientsIds, userIngredientsIds);

                        Set<Ingredient> missingIngredients = new TreeSet<>();

                        for (Long id : missinIngredientsIds) {
                            missingIngredients.add(sqlIngredientsDAO.getById(id));
                        }

                        suggestion.setMissingIngredients(missingIngredients);

                    }


                }
        );

        return suggestions;
    }


}
