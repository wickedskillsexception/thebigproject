package com.siit.thebigproject.db.sql;

import com.siit.thebigproject.base.Recipe;
import com.siit.thebigproject.db.Crud;

import java.util.Collection;

public class RecipeDAO implements Crud<Recipe> {
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
