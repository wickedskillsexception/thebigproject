package com.siit.thebigproject.base;

import java.util.List;

public class Recipe extends ObjectId {

   private String name;
   private List<Ingredient> ingredientsList;
   private String preparation;
   private int calories;
   private List<RecipeType> recipeTypes;


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredientsList=\n" + ingredientsList.toString() +
                ", preparation='" + preparation + '\'' +
                ", calories=" + calories +
                ", recipeTypes=" + recipeTypes +
                '}' +"\n";
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

   public int getCalories() {
      return calories;
   }

   public void setCalories(int calories) {
      this.calories = calories;
   }

   public List<RecipeType> getRecipeTypes() {
      return recipeTypes;
   }

   public void setRecipeTypes(List<RecipeType> recipeTypes) {
      this.recipeTypes = recipeTypes;
   }
}

