package com.siit.thebigproject.domain;

import java.util.List;

public class Recipe extends ObjectId {

    private String name;
    private List<Ingredient> ingredientsList;
    private String preparation;
    private int preparationTime;
    private List<String> recipeTypes;
    private String image;
    private int smartPoints;

    public Recipe() {
    }

    public Recipe(String name, List<Ingredient> ingredientsList, String preparation, int preparationTime, List<String> recipeTypes, String image, int smartPoints) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.preparation = preparation;
        this.preparationTime = preparationTime;
        this.recipeTypes = recipeTypes;
        this.image = image;
        this.smartPoints = smartPoints;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public List<String> getRecipeTypes() {
        return recipeTypes;
    }

    public void setRecipeTypes(List<String> recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSmartPoints() {
        return smartPoints;
    }

    public void setSmartPoints(int smartPoints) {
        this.smartPoints = smartPoints;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredientsList=" + ingredientsList +
                ", preparation='" + preparation + '\'' +
                ", preparationTime=" + preparationTime +
                ", recipeTypes=" + recipeTypes +
                ", image='" + image + '\'' +
                ", smartPoints=" + smartPoints +
                '}';
    }
}

