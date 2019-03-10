package com.siit.thebigproject.domain;

public class FridgeIngredient extends ObjectId {

    private Double quantity;
    private Integer fridgeId;

    public FridgeIngredient(long id, Double quantity, Integer fridgeId) {
        setId(id); // Este defapt ingredientId
        this.quantity = quantity;
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
