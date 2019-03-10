package com.siit.thebigproject.domain;

import java.util.Objects;

public class RecipeIngredient extends ObjectId {

    private Integer ingredientId;
    private Double quantity;

    public RecipeIngredient(long id, Integer ingredientId, Double quantity) {
        setId(id); // este recipeId
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public Integer getIngredientId() {
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
}
