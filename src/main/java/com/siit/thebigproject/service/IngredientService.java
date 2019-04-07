package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLIngredientsDAO;
import com.siit.thebigproject.domain.Ingredient;
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
public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private SQLIngredientsDAO ingredientsDAO;

    public Collection<Ingredient> listAll() {
        return ingredientsDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting ingredient with id: " + id);
        Ingredient ingredient = null;
        ingredient = ingredientsDAO.getById(id);

        return ingredientsDAO.delete(ingredient);
    }

    public Ingredient get(Long id) {
        LOGGER.debug("Getting ingredient with id: " + id);
        return ingredientsDAO.getById(id);
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
        ingredientsDAO.update(ingredient);
    }

    public SQLIngredientsDAO getIngredientsDAO() {
        return ingredientsDAO;
    }

    public void setIngredientsDAO(SQLIngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
