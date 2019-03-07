package com.siit.thebigproject.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class parseRecipeFromFileToObjectTest {

    parseRecipeFromFileToObject parseRecipeFromFileToObject = new parseRecipeFromFileToObject();

    @Test
    public void getRecipeListFromJSonTest()  {

        //parseRecipeFromFileToObject.getRecipeListFromJSon();
        //parseRecipeFromFileToObject.printAllIngredients();
        //parseRecipeFromFileToObject.printRecipes();
        parseRecipeFromFileToObject.getAllIngredientsList();
        parseRecipeFromFileToObject.printAllIngredients();
        //assertEquals(100, parseRecipeFromFileToObject.getRecipeList().size());

    }

}
