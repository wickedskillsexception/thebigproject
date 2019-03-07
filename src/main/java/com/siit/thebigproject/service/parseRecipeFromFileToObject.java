package com.siit.thebigproject.service;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeParser {

    private List<Recipe> recipeList = new ArrayList<>();
    private List<String> allIngredients = new ArrayList<>();

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public List<String> getAllIngredients() {
        return allIngredients;
    }

    public void getRecipeListFromJSon() {


        String recipes = "recipes" + File.separator + "retete.json";
        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(recipes));
            JSONArray a = (JSONArray) jsonObject.get("recipes");

            for (Object o : a) {

                List<String> recipeProperties = new ArrayList<>();
                JSONObject jo = (JSONObject) o;
                List<Ingredient> ingredients = new ArrayList<>();

                String name = (String) jo.get("title");
                ingredients = getIngredientsList(o);
                String preparation = (String) jo.get("instructions");
                int preparationTime = Integer.parseInt(jo.get("readyInMinutes").toString());
                recipeProperties = getRecipeProperties(o);
                String image = (String) jo.get("image");
                int smartPoints = Integer.parseInt(jo.get("weightWatcherSmartPoints").toString());

                recipeList.add(new Recipe(name, ingredients, preparation, preparationTime, recipeProperties, image, smartPoints));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private List<Ingredient> getIngredientsList(Object o) {

        List<Ingredient> ingredients = new ArrayList<>();
        JSONObject jo = (JSONObject) o;

        JSONArray a = (JSONArray) jo.get("extendedIngredients");

        for (Object obj : a) {

            //System.out.println(obj.toString());
            JSONObject jobj = (JSONObject) obj;
            String ingredientName = (String) jobj.get("name");
            if (!checkIngredient(ingredientName)) {
                allIngredients.add(ingredientName);
            }
            JSONObject measures = (JSONObject) jobj.get("measures");
            JSONObject metricMeasures = (JSONObject) measures.get("metric");

            String ingredientUnit = (String) metricMeasures.get("unitLong");
            Double ingredientQuantity = new Double(metricMeasures.get("amount").toString());
            ingredients.add(new Ingredient(ingredientName, ingredientUnit, ingredientQuantity));

        }
        return ingredients;
    }

    private boolean checkIngredient(String ingredientName) {

        boolean b = false;
        for (String a : allIngredients) {
            if (a.equals(ingredientName)) {
                b = true;
            }
        }
        return b;
    }

    public List<String> getRecipeProperties(Object o) {

        List<String> recipeProperies = new ArrayList<>();
        JSONObject jo = (JSONObject) o;

        if (jo.get("vegetarian").toString() == "true") {
            recipeProperies.add(RecipeType.VEGETARIAN.getRecipeTypeValue());
        }

        if (jo.get("vegan").toString() == "true") {
            recipeProperies.add(RecipeType.VEGAN.getRecipeTypeValue());
        }

        if (jo.get("glutenFree").toString() == "true") {
            recipeProperies.add(RecipeType.GLUTENFREE.getRecipeTypeValue());
        }

        if (jo.get("dairyFree").toString() == "true") {
            recipeProperies.add(RecipeType.DAIRYFREE.getRecipeTypeValue());
        }

        if (jo.get("veryHealthy").toString() == "true") {
            recipeProperies.add(RecipeType.VERYHEALTHY.getRecipeTypeValue());
        }

        if (jo.get("cheap").toString() == "true") {
            recipeProperies.add(RecipeType.CHEAP.getRecipeTypeValue());
        }

        if (jo.get("veryPopular").toString() == "true") {
            recipeProperies.add(RecipeType.VERYPOPULAR.getRecipeTypeValue());
        }

        if (jo.get("sustainable").toString() == "true") {
            recipeProperies.add(RecipeType.SUSTEINABLE.getRecipeTypeValue());
        }

        if (jo.get("ketogenic").toString() == "true") {
            recipeProperies.add(RecipeType.LOWCAL.getRecipeTypeValue());
        }

        if (jo.get("lowFodmap").toString() == "true") {
            recipeProperies.add(RecipeType.COMFORT_FOOD.getRecipeTypeValue());
            recipeProperies.add(RecipeType.LOWFOODMAP.getRecipeTypeValue());
        }

        return recipeProperies;
    }

    public void printRecipes() {
        for (Recipe r : recipeList) {
            System.out.println(r);
        }
    }

    public void printAllIngredients() {
        for (String s : allIngredients) {
            System.out.println(s);
        }
    }
}
