package com.siit.thebigproject.domain;

public class Suggestion extends ObjectId {

    private Recipe recipe;
    private boolean hasAllIngredients;
    private double matchPercent;
    private String missingIngredients;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean isHasAllIngredients() {
        return hasAllIngredients;
    }

    public void setHasAllIngredients(boolean hasAllIngredients) {
        this.hasAllIngredients = hasAllIngredients;
    }

    public double getMatchPercent() {
        return matchPercent;
    }

    public void setMatchPercent(double matchPercent) {
        this.matchPercent = matchPercent;
    }

    public String getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(String missingIngredients) {
        this.missingIngredients = missingIngredients;
    }
}
