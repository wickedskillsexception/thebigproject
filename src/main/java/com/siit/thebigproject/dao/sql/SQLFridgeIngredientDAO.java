package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.FridgeIngredient;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SQLFridgeIngredientDAO extends SQLBaseDAO<FridgeIngredient> {

    @Autowired
    ConnectionDb db;

    @Override
    public void add(FridgeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;
            PreparedStatement crtValPs = null;

            try {
                insertionPs = connection.prepareStatement("INSERT INTO fridge_ingredients(fridge_id, ingredient_id, quantity) values( ?, ?, ?)");
                insertionPs.setLong(1, ingredient.getFridgeId());
                insertionPs.setLong(1, ingredient.getIngredientId());
                insertionPs.setDouble(1, ingredient.getQuantity());
                insertionPs.executeUpdate();

                crtValPs = connection.prepareStatement("SELECT CURRVAL('fridge_ingredients_ids')");
                ResultSet resultSet = crtValPs.executeQuery();
                resultSet.next();
                ingredient.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                System.err.println("Cannot insert fridge ingredient: " + e.getMessage());
            } finally {
                if (insertionPs != null && crtValPs!= null) {
                    try {
                        insertionPs.close();
                        crtValPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public Collection<FridgeIngredient> getAll() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from fridge_ingredients;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<FridgeIngredient> ingredients = new ArrayList<>();

                while (resultSet.next()) {
                    FridgeIngredient ingredient = mapResultSetToFridge(resultSet);
                    ingredients.add(ingredient);
                }
                return ingredients;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all fridge ingredients: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
            return null;
        }
    }

    private FridgeIngredient mapResultSetToFridge(ResultSet resultSet) throws SQLException {
        FridgeIngredient ingredient = new FridgeIngredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setFridgeId(resultSet.getInt("fridge_id"));
        ingredient.setIngredientId(resultSet.getInt("ingredient_id"));
        ingredient.setQuantity(resultSet.getDouble("quantity"));
        return ingredient;
    }

    @Override
    public FridgeIngredient getById(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from fridge_ingredients WHERE id = ?;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<Fridge> fridges = new ArrayList<>();

                resultSet.next();
                FridgeIngredient ingredient = mapResultSetToFridge(resultSet);
                return ingredient;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve ingredient with specified ID: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
            return null;
        }
    }

    @Override
    public FridgeIngredient update(FridgeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement updatePs = null;

            try {
                updatePs = connection.prepareStatement("UPDATE fridge_ingredients SET fridge_id = ?, ingredient_id = ?, quantity = ? WHERE id = ?");
                updatePs.setLong(1, ingredient.getFridgeId());
                updatePs.setLong(2, ingredient.getIngredientId());
                updatePs.setDouble(1, ingredient.getQuantity());
                updatePs.executeUpdate();

                return ingredient;
            } catch (SQLException e) {
                System.err.println("Cannot update ingredient: " + e.getMessage());
            } finally {
                if (updatePs != null) {
                    try {
                        updatePs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(FridgeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from fridge_ingredients WHERE id = ?;");
                insertionPs.setLong(1, ingredient.getId());
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete ingredient: " + e.getMessage());
            } finally {
                if (insertionPs != null) {
                    try {
                        insertionPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
        }
        return false;
    }

}
