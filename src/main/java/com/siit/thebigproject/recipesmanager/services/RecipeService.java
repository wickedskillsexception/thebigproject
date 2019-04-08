package com.siit.thebigproject.recipesmanager.services;

import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.recipesmanager.dao.sql.SQLRecipesDAO;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.SQLException;
import java.util.List;

public class RecipeService {

    private SQLRecipesDAO sqlRecipesDAO;
    private TheBigProjectDB db;

    public void save(List<Recipe> recipeList) throws TheBigProjectDBException, SQLException {

        db = new TheBigProjectDB();
        sqlRecipesDAO = new SQLRecipesDAO(db);

        int i = 1;
        for (Recipe r : recipeList) {
            if (sqlRecipesDAO.getByRecipeName(r) == null) {
                sqlRecipesDAO.add(r);
                System.out.println(i++);
            } else {
                if (!(sqlRecipesDAO.getByRecipeName(r).getId() > 0)) {
                    sqlRecipesDAO.add(r);
                }
            }
        }
    }

    public long getRecipeIDByName(Recipe recipe) throws TheBigProjectDBException, SQLException {
        db = new TheBigProjectDB();
        sqlRecipesDAO = new SQLRecipesDAO(db);

        return sqlRecipesDAO.getByRecipeName(recipe).getId();
    }
}
