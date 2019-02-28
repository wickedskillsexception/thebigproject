package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class RecipeIngredient {

    @NotEmpty
    private long recipeId;

    @NotEmpty
    private long ingredientId;

    @NotEmpty
    @Max(10)
    private int quantity;

    public RecipeIngredient(long recipeId, long ingredientId, int quantity) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public RecipeIngredient() {
    }
}
