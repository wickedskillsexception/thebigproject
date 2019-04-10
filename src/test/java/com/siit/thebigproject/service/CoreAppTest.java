package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.FridgeIngredientsDAO;
import com.siit.thebigproject.dao.RecipesDAO;
import com.siit.thebigproject.dao.sql.SQLIngredientsDAO;
import com.siit.thebigproject.domain.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoreAppTest extends CoreApp {

    @Autowired
    FridgeIngredientsDAO fridgeIngredientsDAO;
    RecipesDAO recipesDAO;


    @Test
    public void recipeMatcherTest() {



        Fridge userFridge = new Fridge();

        Ingredient ceapa = new Ingredient();
        ceapa.setId(1);

        Ingredient varza = new Ingredient();
        varza.setId(2);


        List<FridgeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(fridgeIngredientsDAO.getById(ceapa.getId()));
        ingredientList.add(fridgeIngredientsDAO.getById(varza.getId()));

        userFridge.setIngredientList(ingredientList);


        List<Recipe> recipeList = recipesDAO.getAll().stream().collect(Collectors.toList());

        CoreApp coreApp = new CoreApp();


        Map <Double, Recipe> matches = coreApp.recipeMatcher(userFridge, recipeList);

        String a = Arrays.toString(matches.entrySet().toArray());
        System.out.println(a);




    }

}