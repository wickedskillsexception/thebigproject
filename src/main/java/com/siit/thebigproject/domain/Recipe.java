package com.siit.thebigproject.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Recipe extends ObjectId {

    private String name;
    private List<RecipeIngredient> ingredientsList;
    private String preparation;
    private int preparationTime;
    private String recipeTypes;
    private String image;
    private int smartPoints;
    private String ingredients;

    public Recipe() {
    }

    public Recipe(long id, String name, List<RecipeIngredient> ingredientsList, String preparation, int preparationTime, String recipeTypes, String image, int smartPoints) {
        setId(id);
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.preparation = preparation;
        this.preparationTime = preparationTime;
        this.recipeTypes = recipeTypes;
        this.image = image;
        this.smartPoints = smartPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientsList(List<RecipeIngredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setRecipeTypes(String recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSmartPoints(int smartPoints) {
        this.smartPoints = smartPoints;
    }

    public String getName() {
        return name;
    }

    public List<RecipeIngredient> getIngredientsList() {
        return ingredientsList;
    }

    public String getPreparation() {
        return preparation;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getRecipeTypes() {
        return recipeTypes;
    }

    public String getImage() {
        return image;
    }

    public int getSmartPoints() {
        return smartPoints;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredientsList=" + ingredientsList +
                ", preparation='" + preparation + '\'' +
                ", preparationTime=" + preparationTime +
                ", recipeTypes='" + recipeTypes + '\'' +
                ", image='" + image + '\'' +
                ", smartPoints=" + smartPoints +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return preparationTime == recipe.preparationTime &&
                smartPoints == recipe.smartPoints &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(ingredientsList, recipe.ingredientsList) &&
                Objects.equals(preparation, recipe.preparation) &&
                Objects.equals(recipeTypes, recipe.recipeTypes) &&
                Objects.equals(image, recipe.image) &&
                Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredientsList, preparation, preparationTime, recipeTypes, image, smartPoints, ingredients);
    }
}

