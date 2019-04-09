package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.FridgeIngredient;

import java.util.Collection;

public interface FridgeIngredientsDAO extends BaseDAO<FridgeIngredient> {

    public boolean deleteByFridgeId(long fridgeId);
    public Collection<FridgeIngredient> getByFridgeId(long fridgeId);
    public boolean deleteByIngredientId(long ingredientId);
    public boolean deleteByIds(long fridge_id, long ingredient_id);
}
