package com.siit.thebigproject.domain;

import java.util.List;

public class Fridge extends ObjectId {

    private int userId;
    private List<FridgeIngredient> ingredientList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FridgeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<FridgeIngredient> ingredientList) {
        this.ingredientList = this.ingredientList;
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "userId=" + userId +
                ", ingredientList=" + ingredientList +
                '}';
    }

}
