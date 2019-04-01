package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLIngredientsDAO;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private SQLIngredientsDAO ingredientDAO;

    public Collection<Ingredient> listAll() {
        try {
            return ingredientDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        Ingredient usr = null;
        try {
            usr = ingredientDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (usr != null) {
            try {
                ingredientDAO.delete(usr);
            } catch (DbException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public Ingredient get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return ingredientDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Ingredient ingredient) throws ValidationException {
        LOGGER.debug("Saving: " + ingredient);
//        validate(User);

        try {
            ingredientDAO.update(ingredient);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLIngredientsDAO getIngredientDAO() {

        return ingredientDAO;
    }

    public void setIngredientDAO(SQLIngredientsDAO ingredientDAO) {

        this.ingredientDAO = ingredientDAO;
    }
}
