package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Recipe;

import java.sql.SQLException;

public interface RecipesDAO extends BaseDAO<Recipe> {

    public boolean deleteByName(String name) throws DbException, SQLException;

}
