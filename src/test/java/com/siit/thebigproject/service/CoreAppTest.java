/*
package com.siit.thebigproject.service;


import com.siit.thebigproject.domain.*;

import com.siit.thebigproject.recipesmanager.ParseRecipeFromFileToObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// @RunWith(SpringJUnit4ClassRunner.class)
public class CoreAppTest extends CoreApp {

    ParseRecipeFromFileToObject recipeFromFileToObject = new ParseRecipeFromFileToObject();


    @Test
    public void recipeMatcherTest() {

        recipeFromFileToObject.getRecipeListFromJSon();
        List<Recipe> recipes = recipeFromFileToObject.getRecipeList();
        recipeFromFileToObject.getAllIngredientsList();
        List<Ingredient> ingredients = recipeFromFileToObject.getAllIngredients();

        Fridge userFridge = new Fridge();
        userFridge.setId(1);
        userFridge.setUserId(1);

        Ingredient ceapa = new Ingredient();
        ceapa.setId(1);

        Ingredient varza = new Ingredient();
        varza.setId(2);

        FridgeIngredient ceapaFridge = new FridgeIngredient();
        ceapaFridge.setIngredientId(1);
        ceapaFridge.setFridgeId(1);
        FridgeIngredient varzaFridge = new FridgeIngredient();
        varzaFridge.setIngredientId(2);
        varzaFridge.setFridgeId(1);

        List<FridgeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(ceapaFridge);
        ingredientList.add(varzaFridge);

        userFridge.setIngredientList(ingredientList);

        CoreApp coreApp = new CoreApp();

        Map<Double, Recipe> matches = coreApp.recipeMatcher(userFridge, recipes);

        String a = Arrays.toString(matches.entrySet().toArray());
        System.out.println(a);


    }

}*/
