package com.siit;

import com.siit.thebigproject.service.ParseRecipeFromFileToObject;
import com.siit.thebigproject.service.ParseRecipeFromWebServiceToFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThebigprojectApplication {
    public static void main(String[] args) {

        //SpringApplication.run(ThebigprojectApplication.class, args);
        ParseRecipeFromFileToObject parseRecipeFromFileToObject = new ParseRecipeFromFileToObject();
        parseRecipeFromFileToObject.getAllIngredientsList();
        parseRecipeFromFileToObject.printAllIngredients();
        parseRecipeFromFileToObject.getRecipeListFromJSon();
        parseRecipeFromFileToObject.printRecipes();

    }
}


