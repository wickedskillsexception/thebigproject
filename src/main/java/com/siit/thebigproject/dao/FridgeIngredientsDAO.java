package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.FridgeIngredient;

import java.sql.SQLException;
import java.util.Collection;

public interface FridgeIngredientsDAO extends BaseDAO<FridgeIngredient> {

    public boolean deleteByFridgeId(long fridgeId) throws DbException, SQLException;
    public Collection<FridgeIngredient> getByFridgeId(long fridgeId) throws DbException, SQLException;
}
