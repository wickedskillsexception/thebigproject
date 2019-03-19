package com.siit.thebigproject.domain;

import java.util.List;

public class Fridge extends ObjectId {

    private List<FridgeIngredient> ingredientList;
    private int userId;

    public Fridge(List<FridgeIngredient> ingredientList, int userId) {
        this.ingredientList = ingredientList;
        this.userId = userId;
    }

    public void setIngredientList(List<FridgeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
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
