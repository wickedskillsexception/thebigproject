package com.siit.thebigproject.service;

import com.siit.thebigproject.base.Ingredient;

import com.siit.thebigproject.db.sql.IngredientDAO;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IngredientDAO ingredientDAO;

    public Collection<Ingredient> listAll() {
        return ingredientDAO.readAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        Ingredient usr = ingredientDAO.getById(id);
        if (usr != null) {
            ingredientDAO.delete(usr);
            return true;
        }

        return false;
    }

    public Ingredient get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        return ingredientDAO.getById(id);

    }

    public void save(Ingredient ingredient) throws ValidationException {
        LOGGER.debug("Saving: " + ingredient);
//        validate(User);

        ingredientDAO.update(ingredient);
    }

    public IngredientDAO getIngredientDAO() {

        return ingredientDAO;
    }

    public void setUserDAO(IngredientDAO userDAO) {

        this.ingredientDAO = userDAO;
    }
}
