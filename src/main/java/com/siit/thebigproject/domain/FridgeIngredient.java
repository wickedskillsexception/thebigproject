package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class FridgeIngredient extends Ingredient {

    @NotEmpty
    @Max(10)
    private double quantity;

    @NotEmpty
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
