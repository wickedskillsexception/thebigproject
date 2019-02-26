package com.siit.thebigproject.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RecipeParserTest {

    RecipeParser recipeParser = new RecipeParser();

    @Test
    public void getRecipeListFromJSonTest()  {

        recipeParser.getRecipeListFromJSon();
        //recipeParser.printAllIngredients();
        recipeParser.printRecipes();
        assertEquals(100, recipeParser.getRecipeList().size());

    }

}
