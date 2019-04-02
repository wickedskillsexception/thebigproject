package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.RecipeWithType;

import java.util.List;

public interface RecipesWithTypesDAO {

    public List<RecipeWithType> getByRecipeId(Long recipeId);
}
