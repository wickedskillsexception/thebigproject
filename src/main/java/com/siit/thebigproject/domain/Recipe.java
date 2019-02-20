package com.siit.thebigproject.domain;

import java.util.List;

public class Recipe extends ObjectId {
   private String name;
   private List<Ingredient> ingredientsList;
   private String preparation;
   private int calories;
   private RecipeType recipeType;


}
