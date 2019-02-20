package com.siit.thebigproject.base;

import java.util.List;

public class Fridge extends ObjectId{
    private List<Ingredient> ingredientSet;

    public List<Ingredient> getIngredientList() {
        return ingredientSet;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientSet = ingredientList;
    }
}
