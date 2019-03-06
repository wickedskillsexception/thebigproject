package com.siit.thebigproject.domain;


import javax.validation.constraints.NotNull;

public class FridgeIngredient extends Ingredient {

    @NotNull(message = "Please insert the quantity")
    private double quantity;

    @NotNull
    private long fridgeId;

    public FridgeIngredient() {
    }

    public FridgeIngredient(long fridgeId, String name, String unit, double quantity) {
        super(name, unit);
        this.fridgeId = fridgeId;
        this.quantity = quantity;
    }

    public long getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(long fridgeId) {
        this.fridgeId = fridgeId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
