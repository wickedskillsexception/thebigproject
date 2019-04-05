package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.RecipeIngredient;

import java.util.Collection;

public interface RecipeIngredientsDAO {

    public boolean deleteByRecipeId(long fridgeId);
    public Collection<RecipeIngredient> getByRecipeId(long fridgeId);
}
