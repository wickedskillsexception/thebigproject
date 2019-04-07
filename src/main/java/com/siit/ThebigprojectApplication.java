package com.siit;

import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.recipesManager.IngredientsToDBTable;
import com.siit.thebigproject.service.recipesManager.ParseRecipeFromFileToObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThebigprojectApplication {

//    @Autowired
//    private static IngredientsToDBTable ingredientsToDBTable;

    static IngredientsToDBTable   ingredientsToDBTable = new IngredientsToDBTable();

    public static void main(String[] args) {
        try {
            ingredientsToDBTable.insertIngredientsToDB();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        SpringApplication.run(ThebigprojectApplication.class, args);




    }
}


