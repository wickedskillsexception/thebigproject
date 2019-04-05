package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.Recipe;

public interface RecipesDAO extends BaseDAO<Recipe> {

    public boolean deleteById(long recipeId);

}
