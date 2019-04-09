package com.siit.thebigproject.service;


import com.siit.thebigproject.dao.sql.SQLFridgeIngredientsDAO;
import com.siit.thebigproject.domain.FridgeIngredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class FridgeIngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeIngredientService.class);

    @Autowired
    private SQLFridgeIngredientsDAO fridgeIngredientsDAO;

    public Collection<FridgeIngredient> listAll() {
        return fridgeIngredientsDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting fridge ingredient with id: " + id);
        FridgeIngredient fridgeIngredient = null;
        fridgeIngredient = fridgeIngredientsDAO.getById(id);

        return fridgeIngredientsDAO.delete(fridgeIngredient);

    }

    public boolean checkIfExists(FridgeIngredient fridgeIngredient) {
        boolean exists = false;
        Collection<FridgeIngredient> all = fridgeIngredientsDAO.getAll();
        for(FridgeIngredient f: all){
            if(fridgeIngredient.getFridgeId() == f.getFridgeId() && fridgeIngredient.getIngredientId() == f.getIngredientId()){
                exists = true;
            }
        }
        return exists;
    }

    public FridgeIngredient add(FridgeIngredient fridgeIngredient) {
        return fridgeIngredientsDAO.update(fridgeIngredient);
    }

    public boolean deleteByIds(Long fridge_id, long ingredient_id) {
        return fridgeIngredientsDAO.deleteByIds(fridge_id, ingredient_id);

    }

    public FridgeIngredient get(Long id) {
        LOGGER.debug("Getting fridge ingredient with id: " + id);
        return fridgeIngredientsDAO.getById(id);
    }

    public List<FridgeIngredient> getByFridgeId(Long id) {
        return fridgeIngredientsDAO.getByFridgeId(id);
    }

    public void save(FridgeIngredient fridgeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + fridgeIngredient);
        List<String> errors = new LinkedList<>();

        if (StringUtils.isEmpty(fridgeIngredient.getFridgeId())) {
            errors.add("Fridge ingredient FridgeId is Empty");
        }
        if (StringUtils.isEmpty(fridgeIngredient.getIngredientId())) {
            errors.add("Fridge ingredient IngredientId is Empty");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
        fridgeIngredientsDAO.update(fridgeIngredient);
    }

    public SQLFridgeIngredientsDAO getFridgeIngredientsDAO() {
        return fridgeIngredientsDAO;
    }

    public void SQLFridgeIngredientsDAO(SQLFridgeIngredientsDAO fridgeIngredientsDAO) {
        this.fridgeIngredientsDAO = fridgeIngredientsDAO;
    }
}