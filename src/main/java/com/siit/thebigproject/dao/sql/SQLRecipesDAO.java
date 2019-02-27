package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.dao.Crud;

import java.util.Collection;

public class SQLRecipesDAO implements Crud<Recipe> {
    @Override
    public void create(Recipe object) {

    }

    @Override
    public Collection<Recipe> readAll() {
        return null;
    }

    @Override
    public Recipe getById(Long id) {
        return null;
    }

    @Override
    public Recipe update(Recipe object) {
        return null;
    }

    @Override
    public boolean delete(Recipe object) {
        return false;
    }
}
