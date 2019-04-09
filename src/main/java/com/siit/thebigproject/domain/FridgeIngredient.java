package com.siit.thebigproject.domain;

import java.util.Objects;

public class FridgeIngredient extends ObjectId {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FridgeIngredient that = (FridgeIngredient) o;
        return fridgeId == that.fridgeId &&
                ingredientId == that.ingredientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fridgeId, ingredientId);
    }
}
