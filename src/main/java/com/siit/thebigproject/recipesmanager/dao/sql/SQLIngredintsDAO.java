package com.siit.thebigproject.recipesmanager.dao.sql;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDB;
import com.siit.thebigproject.recipesmanager.db.TheBigProjectDBException;

import java.sql.*;

public class SQLIngredintsDAO {

    private TheBigProjectDB db;

    public SQLIngredintsDAO(TheBigProjectDB db) {
        this.db = db;
    }

    public void add(Ingredient ingredient) throws TheBigProjectDBException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO ingredients(name, picture_url) values('" + ingredient.getName() + "', '" + ingredient.getPictureUrl() + "');");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('ingredients_ids')");
            resultSet.next();
            //ingredient.setId(resultSet.getInt(1));
        }
    }

    public Ingredient getByIngredientName(Ingredient  ingredient) throws TheBigProjectDBException, SQLException {
        try (Connection conn = db.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ingredients WHERE name = ? ;");
            statement.setString(1, ingredient.getName());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return mapResultSetToIngredient(resultSet);
            } else {
                return null;
            }
        }
    }

    public Ingredient mapResultSetToIngredient(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        ingredient.setPictureUrl(resultSet.getString("picture_url"));

        return ingredient;
    }


}
