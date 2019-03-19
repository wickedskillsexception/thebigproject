package com.siit.thebigproject.domain;

public class FridgeIngredient extends ObjectId {

    private Double quantity;
    private Integer fridgeId;
    private long ingredientId;

    public FridgeIngredient(Double quantity, Integer fridgeId, long ingredientId) {
        this.quantity = quantity;
        this.fridgeId = fridgeId;
        this.ingredientId = ingredientId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setFridgeId(Integer fridgeId) {
        this.fridgeId = fridgeId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Integer getFridgeId() {
        return fridgeId;
    }

    @Override
    public String toString() {
        return "FridgeIngredient{" +
                "quantity=" + quantity +
                ", fridgeId=" + fridgeId +
                '}';
    }
}
