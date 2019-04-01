package com.siit.thebigproject.domain;

import java.util.List;

public class Fridge extends ObjectId {

    private List<FridgeIngredient> ingredientList;
    private long userId;

    public Fridge() {
    }

    public Fridge(List<FridgeIngredient> ingredientList, int userId) {
        this.ingredientList = ingredientList;
        this.userId = userId;
    }

    public void setIngredientList(List<FridgeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<FridgeIngredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "ingredientList=" + ingredientList +
                ", userId=" + userId +
                '}';
    }
}
