package com.siit.thebigproject.domain;

import java.util.List;
import java.util.Objects;

public class UserPreferences extends ObjectId {
    private int userId;
    private List<RecipeType> recipeTypes;
    private List<Ingredient> ingredients;
    private int smartPoints;

    public UserPreferences(long id, int userId, List<RecipeType> recipeTypes, List<Ingredient> ingredients, int smartPoints) {
        setId(id);
        this.userId = userId;
        this.recipeTypes = recipeTypes;
        this.ingredients = ingredients;
        this.smartPoints = smartPoints;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRecipeTypes(List<RecipeType> recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSmartPoints(int smartPoints) {
        this.smartPoints = smartPoints;
    }

    public int getUserId() {
        return userId;
    }

    public List<RecipeType> getRecipeTypes() {
        return recipeTypes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getSmartPoints() {
        return smartPoints;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "userId=" + userId +
                ", recipeTypes=" + recipeTypes +
                ", ingredients=" + ingredients +
                ", smartPoints=" + smartPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferences that = (UserPreferences) o;
        return userId == that.userId &&
                smartPoints == that.smartPoints &&
                Objects.equals(recipeTypes, that.recipeTypes) &&
                Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, recipeTypes, ingredients, smartPoints);
    }
}
