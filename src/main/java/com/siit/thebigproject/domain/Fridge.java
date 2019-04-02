package com.siit.thebigproject.domain;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fridge fridge = (Fridge) o;
        return userId == fridge.userId &&
                Objects.equals(ingredientList, fridge.ingredientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientList, userId);
    }
}
