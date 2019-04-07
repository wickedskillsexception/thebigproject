package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLIngredientsDAO;
import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.h2.message.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private SQLIngredientsDAO ingredientsDAO;

    public Collection<Ingredient> listAll() {
        try {
            return ingredientsDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;

    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        Ingredient usr = null;
        try {
            usr = ingredientsDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (usr != null) {
            try {
                ingredientsDAO.delete(usr);
            } catch (DbException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public Ingredient get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return ingredientsDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Ingredient ingredient) throws ValidationException {
        LOGGER.debug("Saving: " + ingredient);
        List<String> errors = new LinkedList<>();

        if (StringUtils.isEmpty(ingredient.getName())) {
            errors.add("Ingredient name is Empty");
        }

        if (StringUtils.isEmpty(ingredient.getPictureUrl())) {
            errors.add("Ingredient picture URL is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }


        try {
            ingredientsDAO.update(ingredient);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public SQLIngredientsDAO getIngredientsDAO() {

        return ingredientsDAO;
    }

    public void setIngredientsDAO(SQLIngredientsDAO ingredientsDAO) {

        this.ingredientsDAO = ingredientsDAO;
    }
}
