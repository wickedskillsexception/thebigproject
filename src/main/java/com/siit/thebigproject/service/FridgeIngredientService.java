package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLFridgeIngredientsDAO;
import com.siit.thebigproject.domain.FridgeIngredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FridgeIngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeIngredientService.class);

    @Autowired
    private SQLFridgeIngredientsDAO fridgeIngredientDAO;

    public Collection<FridgeIngredient> listAll() {


            return fridgeIngredientDAO.getAll();

    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        FridgeIngredient frd = null;
            frd = fridgeIngredientDAO.getById(id);

        if (frd != null) {

                fridgeIngredientDAO.delete(frd);

            return true;
        }

        return false;
    }

    public FridgeIngredient get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);

            return fridgeIngredientDAO.getById(id);


    }

    public void save(FridgeIngredient fridgeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + fridgeIngredient);
//        validate(User);

            fridgeIngredientDAO.update(fridgeIngredient);

    }

    public SQLFridgeIngredientsDAO getFridgeIngredientDAO() {

        return fridgeIngredientDAO;
    }

    public void SQLFridgeIngredientDAO(SQLFridgeIngredientsDAO fridgeIngredientDAO) {

        this.fridgeIngredientDAO = fridgeIngredientDAO;
    }
}
