package com.siit.thebigproject.domain;

import java.util.Objects;

public class FridgeIngredient extends ObjectId {

    private Double quantity;
    private long fridgeId;
    private long ingredientId;

    public FridgeIngredient() {
    }

    public FridgeIngredient(Double quantity, long fridgeId, long ingredientId) {
        this.quantity = quantity;
        this.fridgeId = fridgeId;
        this.ingredientId = ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setFridgeId(long fridgeId) {
        this.fridgeId = fridgeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public long getFridgeId() {
        return fridgeId;
    }

    @Override
    public String toString() {
        return "FridgeIngredient{" +
                "quantity=" + quantity +
                ", fridgeId=" + fridgeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FridgeIngredient that = (FridgeIngredient) o;
        return fridgeId == that.fridgeId &&
                ingredientId == that.ingredientId &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, fridgeId, ingredientId);
    }
}
