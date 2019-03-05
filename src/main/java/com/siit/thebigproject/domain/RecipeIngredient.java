package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class RecipeIngredient extends Ingredient {


    @NotEmpty
    @Max(10)
    private double quantity;

    private long recipeId;

    public RecipeIngredient() {
    }

    public RecipeIngredient(long recipeId, String name, String unit, double quantity) {
        super(name, unit);
        this.recipeId = recipeId;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}

