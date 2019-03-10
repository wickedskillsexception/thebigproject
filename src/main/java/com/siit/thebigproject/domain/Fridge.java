package com.siit.thebigproject.domain;

import java.util.List;

public class Fridge extends ObjectId {

    private List<FridgeIngredient> ingredientList;
    private int userId;

    public List<FridgeIngredient> getIngredientList() {
        return ingredientList;
    }

    public Fridge(long id, List<FridgeIngredient> ingredientList, int userId) {
        setId(id);
        this.ingredientList = ingredientList;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "ingredientList=" + ingredientList +
                ", userId=" + userId +
                '}';
    }
}
