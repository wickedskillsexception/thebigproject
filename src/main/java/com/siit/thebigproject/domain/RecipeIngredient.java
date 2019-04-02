package com.siit.thebigproject.domain;

import java.util.Objects;

public class RecipeIngredient extends ObjectId {

    private long recipeId;
    private long ingredientId;
    private Double quantity;

    public RecipeIngredient(long recipeId, long ingredientId, Double quantity) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public RecipeIngredient() {
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "ingredientId=" + ingredientId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return recipeId == that.recipeId &&
                ingredientId == that.ingredientId &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId, quantity);
    }
}
