package com.siit.thebigproject.domain;

public class FridgeIngredient extends Ingredient {

    private long fridgeId;
    private long ingredientId;

    public FridgeIngredient() {
    }

    public FridgeIngredient(long fridgeId, long ingredientId) {
        this.fridgeId = fridgeId;
        this.ingredientId = ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setFridgeId(long fridgeId) {
        this.fridgeId = fridgeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public long getFridgeId() {
        return fridgeId;
    }

    @Override
    public String toString() {
        return "FridgeIngredient{" +
                "fridgeId=" + fridgeId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
