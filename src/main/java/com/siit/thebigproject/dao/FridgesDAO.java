package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;

import java.sql.SQLException;
import java.util.Collection;

public interface FridgesDAO extends BaseDAO<Fridge> {

    public Collection<Fridge> getAllWithIngredients();

    public String getByIdWithUserDetails(Long id) throws DbException, SQLException;

    public StringBuilder getAllWithUserDetails() throws DbException, SQLException;

}
