package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

public class FridgeIngredient {

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
}
