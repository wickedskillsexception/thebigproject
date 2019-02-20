package com.siit.thebigproject.service;

import com.siit.thebigproject.base.Fridge;
import com.siit.thebigproject.base.Ingredient;
import com.siit.thebigproject.base.Recipe;
import static com.siit.thebigproject.base.MeasurmentUnit.*;
import static com.siit.thebigproject.base.RecipeType.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoreAppTest extends CoreApp {

    @Test
    public void recipeMatcher() {


        Fridge userFridge = new Fridge();

        List<Recipe> recipeList = new ArrayList<>();


        Recipe sarmale = new Recipe();
        sarmale.setName("Sarmale");
        sarmale.setRecipeTypes(Arrays.asList(VEGAN,VEGETARIAN));
        List<Ingredient> ingredienteSarmale = new ArrayList<>();
        ingredienteSarmale.add(new Ingredient("ceapa", PCS, 1));
        ingredienteSarmale.add(new Ingredient("carne porc", GRAMS, 500));
        ingredienteSarmale.add(new Ingredient("orez", GRAMS, 200));
        sarmale.setIngredientsList(ingredienteSarmale);

        Recipe pilaf = new Recipe();
        pilaf.setName("Pilaf");
        List<Ingredient> ingredientePilaf = new ArrayList<>();
        ingredientePilaf.add(new Ingredient("carne porc", GRAMS, 500));
        ingredientePilaf.add(new Ingredient("orez", GRAMS, 200));
        pilaf.setIngredientsList(ingredientePilaf);


        recipeList.add(sarmale);
        recipeList.add(pilaf);

        userFridge.setIngredientList(ingredienteSarmale);


        CoreApp coreApp = new CoreApp();

        coreApp.recipeMatcher(userFridge, recipeList);






    }
}