package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLRecipesDAO;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.exceptions.ValidationException;
import org.h2.message.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class RecipeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private SQLRecipesDAO recipesDAO;

    public Collection<Recipe> listAll() {
        try {
            return recipesDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        Recipe recipe = null;
        try {
            recipe = recipesDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (recipe != null) {
            try {
                recipesDAO.delete(recipe);
            } catch (DbException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public Recipe get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return recipesDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Recipe recipe) throws ValidationException {
        LOGGER.debug("Saving: " + recipe);
//        validate(Recipe);

        try {
            recipesDAO.update(recipe);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public SQLRecipesDAO getRecipesDAO() {

        return recipesDAO;
    }

    public void setRecipesDAO(SQLRecipesDAO recipesDAO) {

        this.recipesDAO = recipesDAO;
    }
}
