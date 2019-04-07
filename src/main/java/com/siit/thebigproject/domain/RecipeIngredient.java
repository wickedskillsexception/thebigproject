package com.siit.thebigproject.domain;

public class RecipeIngredient extends ObjectId {

    private long recipeId;
    private long ingredientId;

    public RecipeIngredient(long recipeId, long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public RecipeIngredient() {
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeId=" + recipeId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
