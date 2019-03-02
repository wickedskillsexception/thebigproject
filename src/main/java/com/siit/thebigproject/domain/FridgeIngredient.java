package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class FridgeIngredient extends ObjectId {

    @NotEmpty
    private long fridgeId;

    @NotEmpty
    private long ingredientId;

    @NotEmpty
    @Max(10)
    private int quantity;

    public FridgeIngredient() {
    }

    public FridgeIngredient(long fridgeId, long ingredientId, int quantity) {
        this.fridgeId = fridgeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public long getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(long fridgeId) {
        this.fridgeId = fridgeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
