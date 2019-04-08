package com.siit.thebigproject.recipesmanager;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeIngredient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class ParseRecipeFromFileToObject {

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

                String recipeProperties;
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

                if (!checkRecipe(new Recipe(id, name, ingredients, preparation, preparationTime, recipeProperties, image, smartPoints))) {
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

            ingredients.add(new RecipeIngredient(recipeID, ingredientID));

        }
        return ingredients;
    }

    public void getAllIngredientsList() {

        String recipes = "recipes" + File.separator + "retete.json";
        JSONParser parser = new JSONParser();
        List<Ingredient> ingredients = new ArrayList<>();

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

                    String thumbnail = "white.jpg";
                    if (jobj.get("image") != null) {
                        thumbnail = ((String) jobj.get("image")).replaceAll("[()]", "");
                    }

                    String ingredientURL = "https://spoonacular.com/cdn/ingredients_100x100/" + thumbnail;

                    if (!checkIngredient(new Ingredient(id, ingredientName, ingredientURL))) {
                        allIngredients.add(new Ingredient(id, ingredientName, ingredientURL));
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
            if (a.getId() == i.getId()) {
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

    public String getRecipeProperties(Object o) {

        StringBuilder recipeProperies = new StringBuilder();
        JSONObject jo = (JSONObject) o;

        if (jo.get("vegetarian") != null) {
            if (jo.get("vegetarian").toString() == "true") {
                recipeProperies.append("vegetarian, ");
            }
        }

        if (jo.get("vegan") != null) {
            if (jo.get("vegan").toString() == "true") {
                recipeProperies.append("vegan, ");
            }
        }

        if (jo.get("glutenFree") != null) {
            if (jo.get("glutenFree").toString() == "true") {
                recipeProperies.append("glutenFree, ");
            }
        }

        if (jo.get("dairyFree") != null) {
            if (jo.get("dairyFree").toString() == "true") {
                recipeProperies.append("dairyFree, ");
            }
        }

        if (jo.get("veryHealthy") != null) {
            if (jo.get("veryHealthy").toString() == "true") {
                recipeProperies.append("veryHealthy, ");
            }
        }

        if (jo.get("cheap") != null) {
            if (jo.get("cheap").toString() == "true") {
                recipeProperies.append("cheap, ");
            }
        }

        if (jo.get("veryPopular") != null) {
            if (jo.get("veryPopular").toString() == "true") {
                recipeProperies.append("veryPopular, ");
            }
        }

        if (jo.get("sustainable") != null) {
            if (jo.get("sustainable").toString() == "true") {
                recipeProperies.append("sustainable, ");
            }
        }

        if (jo.get("ketogenic") != null) {
            if (jo.get("ketogenic").toString() == "true") {
                recipeProperies.append("ketogenic, ");
            }
        }

        if (jo.get("lowFodmap") != null) {
            if (jo.get("lowFodmap").toString() == "true") {
                recipeProperies.append("comfortFood, ");
                recipeProperies.append("lowFoodMap, ");
            }
        }

        return processRecipeProperties(recipeProperies);
    }

    public String processRecipeProperties(StringBuilder recipeProperies) {

        String stringProperties = recipeProperies.toString();

        if (recipeProperies != null && recipeProperies.length() > 0) {
            stringProperties = stringProperties.substring(0, stringProperties.length() - 1);
            stringProperties = stringProperties.substring(0, 1).toUpperCase() + stringProperties.substring(1, stringProperties.length() - 1);
        } else {
            stringProperties = "Regular";
        }
        return stringProperties;
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
