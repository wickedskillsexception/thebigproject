package com.siit.thebigproject.service.RecipeServiceTest;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.RecipeIngredient;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.IngredientService;
import com.siit.thebigproject.service.RecipeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@TestPropertySource(locations="classpath:test.properties")
public abstract class BaseRecipeServiceTest {
    
    protected abstract RecipeService getRecipeService();
    protected abstract IngredientService getIngredientService();

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
    public void test_add_no_name() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_image() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_smartPoints() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_preparation() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_preparation_time() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_recipe_types() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setIngredientsList(recipeIngredients);
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_no_ingredients_list() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        getRecipeService().save(recipe);

    }

    @Test(expected = ValidationException.class)
    public void test_add_empty() throws ValidationException {
        Recipe recipe = new Recipe();

        getRecipeService().save(recipe);

    }

    @Test
    public void test_add_valid_recipe() throws ValidationException {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);

        getRecipeService().save(recipe);

        Assert.assertEquals(1, getRecipeService().listAll().size());
        Recipe fromDB = getRecipeService().listAll().iterator().next();
        Assert.assertNotNull(fromDB);
        Assert.assertTrue(fromDB.getId() > 0);
        recipe.setId(fromDB.getId());
        Assert.assertEquals(recipe, fromDB);
    }

    @Test
    public void test_delete_inexistent() throws ValidationException {

        Assert.assertFalse(getRecipeService().delete(-10l));

    }

    @Test
    public void test_add_delete() throws Exception {
        Ingredient in = new Ingredient();
        in.setName("onion");
        in.setPictureUrl("www");
        getIngredientService().save(in);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(in.getId());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setName("Soup");
        recipe.setImage("www.google.com");
        recipe.setSmartPoints(10);
        recipe.setPreparation("Boil vegetables");
        recipe.setPreparationTime(20);
        recipe.setRecipeTypes("easy");
        recipe.setIngredientsList(recipeIngredients);

        getRecipeService().save(recipe);

        Assert.assertEquals(1, getRecipeService().listAll().size());
        Recipe fromDB = getRecipeService().listAll().iterator().next();

        Assert.assertTrue(getRecipeService().delete(fromDB.getId()));
        Assert.assertFalse(getRecipeService().delete(fromDB.getId()));
        Assert.assertEquals(0, getRecipeService().listAll().size());
    }



}
