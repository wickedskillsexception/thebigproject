package com.siit.thebigproject.service;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeIngredient;
import com.siit.thebigproject.domain.RecipeType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class parseRecipeFromFileToObject {

    private List<Recipe> recipeList = new ArrayList<>();
    private List<Ingredient> allIngredients = new ArrayList<>();

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public List<Ingredient> getAllIngredients() {
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
                List<RecipeIngredient> ingredients = new ArrayList<>();

                int id = Integer.parseInt(jo.get("id").toString());
                String name = (String) jo.get("title");
                ingredients = getIngredientsList(o);
                String preparation = (String) jo.get("instructions");
                int preparationTime = Integer.parseInt(jo.get("readyInMinutes").toString());
                recipeProperties = getRecipeProperties(o);
                String image = (String) jo.get("image");
                int smartPoints = Integer.parseInt(jo.get("weightWatcherSmartPoints").toString());

                if(!checkRecipe(new Recipe(id, name, ingredients, preparation, preparationTime, recipeProperties, image, smartPoints))) {
                    recipeList.add(new Recipe(id, name, ingredients, preparation, preparationTime, recipeProperties, image, smartPoints));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private List<RecipeIngredient> getIngredientsList(Object o) {

        List<RecipeIngredient> ingredients = new ArrayList<>();
        JSONObject jo = (JSONObject) o;
        int recipeID = Integer.parseInt(jo.get("id").toString());
        JSONArray a = (JSONArray) jo.get("extendedIngredients");


        for (Object obj : a) {

            JSONObject jobj = (JSONObject) obj;
            int ingredientID;
            String ingredientName = (String) jobj.get("name");
            if (jobj.get("id") != null) {
                ingredientID = Integer.parseInt(jobj.get("id").toString());
            } else {
                ingredientID = Objects.hash(ingredientName);
            }
            JSONObject measures = (JSONObject) jobj.get("measures");
            JSONObject metricMeasures = (JSONObject) measures.get("metric");

            //String ingredientUnit = (String) metricMeasures.get("unitLong");
            Double ingredientQuantity = new Double(metricMeasures.get("amount").toString());
            ingredients.add(new RecipeIngredient(recipeID, ingredientID, ingredientQuantity));

        }
        return ingredients;
    }

    public void getAllIngredientsList() {

        String recipes = "recipes" + File.separator + "retete.json";
        JSONParser parser = new JSONParser();
        List<Ingredient> ingredients = new ArrayList<>();
        UnitToGramsConverter unitToGramsConverter = new UnitToGramsConverter();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(recipes));
            JSONArray a = (JSONArray) jsonObject.get("recipes");

            for (Object o : a) {

                JSONObject jo = (JSONObject) o;
                JSONArray o1 = (JSONArray) jo.get("extendedIngredients");

                for (Object obj : o1) {

                    JSONObject jobj = (JSONObject) obj;
                    String ingredientName = (String) jobj.get("name");
                    int id;
                    if (jobj.get("id") != null) {
                        id = Integer.parseInt(jobj.get("id").toString());
                    } else {
                        id = Objects.hash(ingredientName);
                    }
                    JSONObject measures = (JSONObject) jobj.get("measures");
                    JSONObject metricMeasures = (JSONObject) measures.get("metric");
                    String ingredientUnit = (String) metricMeasures.get("unitLong");

                    int factor = unitToGramsConverter.converToGrams(ingredientUnit);

                    if (!checkIngredient(new Ingredient(id, ingredientName, ingredientUnit, factor))) {
                        allIngredients.add(new Ingredient(id, ingredientName, ingredientUnit, factor));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }


    private boolean checkIngredient(Ingredient i) {

        boolean b = false;
        for (Ingredient a : allIngredients) {
            if (a.equals(i)) {
                b = true;
            }
        }
        return b;
    }

    private boolean checkRecipe(Recipe r) {

        boolean b = false;
        for (Recipe recipe : recipeList) {
            if (recipe.equals(r)) {
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
        for (Ingredient s : allIngredients) {
            System.out.println(s);
        }
    }
}
