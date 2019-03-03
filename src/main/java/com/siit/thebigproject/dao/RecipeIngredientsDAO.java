package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.RecipeIngredient;

import java.sql.SQLException;
import java.util.Collection;

public interface RecipeIngredientsDAO {

    public boolean deleteByRecipeId(long fridgeId) throws DbException, SQLException;
    public Collection<RecipeIngredient> getByRecipeId(long fridgeId) throws DbException, SQLException;
}
