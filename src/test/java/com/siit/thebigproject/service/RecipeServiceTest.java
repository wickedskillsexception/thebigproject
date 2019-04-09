package com.siit.thebigproject.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class RecipeServiceTest extends BaseRecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    protected RecipeService getRecipeService(){return recipeService;}

    @Override
    protected IngredientService getIngredientService() {
        return ingredientService;
    }
}
