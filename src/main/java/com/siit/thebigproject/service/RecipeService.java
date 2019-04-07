package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLRecipesDAO;
import com.siit.thebigproject.domain.Recipe;
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
public class RecipeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private SQLRecipesDAO recipesDAO;

    public Collection<Recipe> listAll() {
        return recipesDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting recipe with id: " + id);
        return recipesDAO.deleteById(id);
    }

    public Recipe get(Long id) {
        LOGGER.debug("Getting recipe with id: " + id);
        return recipesDAO.getById(id);
    }

    public void save(Recipe recipe) throws ValidationException {
        LOGGER.debug("Saving: " + recipe);
        List<String> errors = new LinkedList<>();

        if (StringUtils.isEmpty(recipe.getName())) {
            errors.add("Recipe name is Empty");
        }
        if (StringUtils.isEmpty(recipe.getImage())) {
            errors.add("Recipe image is Empty");
        }
        if (StringUtils.isEmpty(recipe.getIngredientsList())) {
            errors.add("Recipe ingredients list is Empty");
        }
        if (StringUtils.isEmpty(recipe.getPreparation())) {
            errors.add("Recipe preparation is Empty");
        }
        if (StringUtils.isEmpty(recipe.getPreparationTime())) {
            errors.add("Recipe prepartion time is Empty");
        }
        if (StringUtils.isEmpty(recipe.getRecipeTypes())) {
            errors.add("Recipe types are Empty");
        }
        if (StringUtils.isEmpty(recipe.getSmartPoints())) {
            errors.add("Recipe smart points is Empty");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
        recipesDAO.update(recipe);
    }

    public SQLRecipesDAO getRecipesDAO() {
        return recipesDAO;
    }

    public void setRecipesDAO(SQLRecipesDAO recipesDAO) {
        this.recipesDAO = recipesDAO;
    }
}
