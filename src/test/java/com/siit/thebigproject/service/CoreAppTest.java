//package com.siit.thebigproject.service;
//
//import com.siit.thebigproject.domain.Fridge;
//import com.siit.thebigproject.domain.Recipe;
//
//import com.siit.thebigproject.domain.RecipeIngredient;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class CoreAppTest extends CoreApp {
//
//    @Test
//    public void recipeMatcher() {
//
//
//        Fridge userFridge = new Fridge();
//
//        List<Recipe> recipeList = new ArrayList<>();
//
//
//        Recipe sarmale = new Recipe();
//        sarmale.setName("Sarmale");
//        sarmale.setRecipeTypes(Arrays.asList("Vegan"));
//        List<RecipeIngredient> ingredienteSarmale = new ArrayList<>();
//
//        ingredienteSarmale.add(new RecipeIngredient(1, "ceapa", "grams", 1));
//        ingredienteSarmale.add(new RecipeIngredient(1, "carne porc", "grams", 500));
//        ingredienteSarmale.add(new RecipeIngredient(1, "orez", "grams", 200));
//        sarmale.setIngredientsList(ingredienteSarmale);
//
//        Recipe pilaf = new Recipe();
//        pilaf.setName("Pilaf");
//        List<RecipeIngredient> ingredientePilaf = new ArrayList<>();
//
//        ingredientePilaf.add(new RecipeIngredient(2, "carne porc", "grams", 500));
//        ingredientePilaf.add(new RecipeIngredient(2, "orez", "grams", 200));
//        pilaf.setIngredientsList(ingredientePilaf);
//
//        recipeList.add(sarmale);
//        recipeList.add(pilaf);
//
//        userFridge.setIngredientList(ingredienteSarmale);
//
//        CoreApp coreApp = new CoreApp();
//
//        coreApp.recipeMatcher(userFridge, recipeList);
//
//
//    }
//}