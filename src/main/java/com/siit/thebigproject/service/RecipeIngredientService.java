package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLRecipeIngredientsDAO;
import com.siit.thebigproject.domain.RecipeIngredient;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RecipeIngredientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeIngredientService.class);

    @Autowired
    private SQLRecipeIngredientsDAO sqlRecipeIngredientsDAO;

    public Collection<RecipeIngredient> listAll() {
        return sqlRecipeIngredientsDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting recipe ingredient with id: " + id);
        LOGGER.debug("Deleting recipe ingredient with id: " + id);
        RecipeIngredient recipeIngredient = null;
        recipeIngredient = sqlRecipeIngredientsDAO.getById(id);

        return sqlRecipeIngredientsDAO.delete(recipeIngredient);
    }

    public RecipeIngredient get(Long id) {
        LOGGER.debug("Getting recipe ingredient with id: " + id);

        return sqlRecipeIngredientsDAO.getById(id);

    }

    public List<RecipeIngredient> getByRecipeId(Long id) {
        return sqlRecipeIngredientsDAO. getByRecipeId(id);

    }

    public void save(RecipeIngredient recipeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + recipeIngredient);
//        List<String> errors = new LinkedList<>();
//
//        if (StringUtils.isEmpty(recipeIngredient.getUserId())) {
//            errors.add("Fridge UserId is Empty");
//        }
//        if (!errors.isEmpty()) {
//            throw new ValidationException(errors.toArray(new String[]{}));
//        }
        sqlRecipeIngredientsDAO.update(recipeIngredient);
    }

    public SQLRecipeIngredientsDAO getSqlRecipeIngredientsDAO() {
        return sqlRecipeIngredientsDAO;
    }

    public void setSqlRecipeIngredientsDAO(SQLRecipeIngredientsDAO sqlRecipeIngredientsDAO) {
        this.sqlRecipeIngredientsDAO = sqlRecipeIngredientsDAO;
    }
}



