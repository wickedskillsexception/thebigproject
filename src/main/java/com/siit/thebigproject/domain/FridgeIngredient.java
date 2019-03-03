package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class FridgeIngredient extends Ingredient {

    @NotEmpty
    @Max(10)
    private double quantity;

    public FridgeIngredient() {
    }

    public FridgeIngredient(String name, String unit, double quantity) {
        super(name, unit);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
