package com.siit.thebigproject.service;


import com.siit.thebigproject.dao.sql.SQLFridgeIngredientsDAO;
import com.siit.thebigproject.domain.FridgeIngredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.h2.message.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FridgeIngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeIngredientService.class);

    @Autowired
    private SQLFridgeIngredientsDAO fridgeIngredientsDAO;

    public Collection<FridgeIngredient> listAll() {

        try {
            return fridgeIngredientsDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        FridgeIngredient frd = null;
        try {
            frd = fridgeIngredientsDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (frd != null) {
            try {
                fridgeIngredientsDAO.delete(frd);
            } catch (DbException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public FridgeIngredient get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return fridgeIngredientsDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(FridgeIngredient fridgeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + fridgeIngredient);
//        validate(User);

        try {
            fridgeIngredientsDAO.update(fridgeIngredient);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public SQLFridgeIngredientsDAO getFridgeIngredientsDAO() {

        return fridgeIngredientsDAO;
    }

    public void SQLFridgeIngredientsDAO(SQLFridgeIngredientsDAO fridgeIngredientsDAO) {

        this.fridgeIngredientsDAO = fridgeIngredientsDAO;
    }
}