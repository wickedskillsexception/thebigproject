package com.siit.thebigproject.service.RecipeServiceTest;

import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.RecipeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;
import java.util.LinkedList;

@TestPropertySource(locations="classpath:test.properties")
public abstract class BaseRecipeServiceTest {
    
    protected abstract RecipeService getRecipeService();

    @After
    public void tearDown() {
        Collection<Recipe> Recipes = new LinkedList<Recipe>(getRecipeService().listAll());

        for (Recipe recipe : Recipes) {
            getRecipeService().delete(recipe.getId());
        }
    }

    @Test
    public void test_empty_get_all() {
        Collection<Recipe> recipes = getRecipeService().listAll();
        Assert.assertTrue(recipes.isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_first_name() throws ValidationException {
        Recipe recipe = new Recipe();

        getRecipeService().save(recipe);

    }




}
