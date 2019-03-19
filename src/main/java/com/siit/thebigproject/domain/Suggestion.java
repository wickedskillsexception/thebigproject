package com.siit.thebigproject.domain;

import java.util.Set;

public class Suggestion extends ObjectId {

    private Recipe recipe;
    private boolean hasAllIngredients;
    private double matchPercent;
    private Set<Ingredient> missingIngredients;


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

    public Set<Ingredient> getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(Set<Ingredient> missingIngredients) {
        this.missingIngredients = missingIngredients;
    }
}
