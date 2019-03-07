package com.siit.thebigproject.base;

public class RecipeIngredient {

    private Integer recipeID;
    private Integer ingredientID;
    private Double quantity;

    public RecipeIngredient(Integer recipeID, Integer ingredientID, Double quantity) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.quantity = quantity;
    }

    public Integer getRecipeID() {
        return recipeID;
    }

    public Integer getIngredientID() {
        return ingredientID;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeID=" + recipeID +
                ", ingredientID=" + ingredientID +
                ", quantity=" + quantity +
                '}';
    }
}
