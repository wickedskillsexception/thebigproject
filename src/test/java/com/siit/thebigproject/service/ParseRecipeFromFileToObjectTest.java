package com.siit.thebigproject.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParseRecipeFromFileToObjectTest {

    ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();

    @Test
    public void getRecipeListFromJSonTest()  {

        parseRecipeFromFileToObject.getRecipeListFromJSon();
        parseRecipeFromFileToObject.printRecipes();
        parseRecipeFromFileToObject.getAllIngredientsList();
        parseRecipeFromFileToObject.printAllIngredients();
        //assertEquals(100, ParseRecipeFromFileToObject.getRecipeList().size());

    }

}
