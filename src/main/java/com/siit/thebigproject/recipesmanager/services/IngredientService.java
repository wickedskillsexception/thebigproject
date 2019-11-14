package com.siit.thebigproject.recipesmanager.services;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.recipesmanager.dao.sql.SQLIngredintsDAO;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.SQLException;
import java.util.List;

public class IngredientService {

    private SQLIngredintsDAO sqlIngredintsDAO;
    private TheBigProjectDB db;

    public void save(List<Ingredient> ingredientList) throws TheBigProjectDBException, SQLException {

        db = new TheBigProjectDB();
        sqlIngredintsDAO = new SQLIngredintsDAO(db);

        int i = 1;
        for (Ingredient l : ingredientList) {
            if (sqlIngredintsDAO.getByIngredientName(l) == null) {
                sqlIngredintsDAO.add(l);
                System.out.println(i++);
            } else {
                if (!(sqlIngredintsDAO.getByIngredientName(l).getId() > 0)) {
                    sqlIngredintsDAO.add(l);
                }
            }
        }
    }

    public long getIngredientIDByName(Ingredient ingredient) throws TheBigProjectDBException, SQLException {

        db = new TheBigProjectDB();
        sqlIngredintsDAO = new SQLIngredintsDAO(db);

        return sqlIngredintsDAO.getByIngredientName(ingredient).getId();
    }
}