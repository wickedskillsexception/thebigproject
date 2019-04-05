package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.Recipe;
import org.h2.message.DbException;

public interface RecipesDAO extends BaseDAO<Recipe> {

    public boolean deleteById(long recipeId);

}
