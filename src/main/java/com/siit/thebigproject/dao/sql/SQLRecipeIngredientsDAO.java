package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.RecipeIngredientsDAO;
import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SQLRecipeIngredientsDAO extends SQLBaseDAO<RecipeIngredient> implements RecipeIngredientsDAO {

    @Autowired
    ConnectionDb db;

    public SQLRecipeIngredientsDAO(ConnectionDb db) {
        this.db = db;
    }

    @Override
    public void add(RecipeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;
            PreparedStatement crtValPs = null;

            try {
                insertionPs = connection.prepareStatement("INSERT INTO recipe_ingredients(recipe_id, ingredient_id, quantity) values( ?, ?, ?)");
                insertionPs.setLong(1, ingredient.getRecipeId());
                insertionPs.setLong(2, ingredient.getIngredientId());
                insertionPs.setDouble(3, ingredient.getQuantity());
                insertionPs.executeUpdate();

                crtValPs = connection.prepareStatement("SELECT CURRVAL('recipe_ingredients_ids')");
                ResultSet resultSet = crtValPs.executeQuery();
                resultSet.next();
                ingredient.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                System.err.println("Cannot insert recipe ingredient: " + e.getMessage());
            } finally {
                if (insertionPs != null && crtValPs != null) {
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
    public List<RecipeIngredient> getAll() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from recipe_ingredients;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<RecipeIngredient> ingredients = new ArrayList<>();

                while (resultSet.next()) {
                    RecipeIngredient ingredient = mapResultSetToFridge(resultSet);
                    ingredients.add(ingredient);
                }
                return ingredients;
            } catch (SQLException e) {
                System.err.println("Cannot retrieve all recipe ingredients: " + e.getMessage());
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

    private RecipeIngredient mapResultSetToFridge(ResultSet resultSet) throws SQLException {
        RecipeIngredient ingredient = new RecipeIngredient();
        ingredient.setId(resultSet.getLong("id"));
        ingredient.setRecipeId(resultSet.getLong("recipe_id"));
        ingredient.setIngredientId(resultSet.getLong("ingredient_id"));
        return ingredient;
    }

    @Override
    public RecipeIngredient getById(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from recipe_ingredients WHERE id = ?;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<Fridge> fridges = new ArrayList<>();

                resultSet.next();
                RecipeIngredient ingredient = mapResultSetToFridge(resultSet);
                return ingredient;
            } catch (SQLException e) {
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
    public List<RecipeIngredient> getByRecipeId(long recipeId) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from recipe_ingredients WHERE recipe_id = ?;");
                selectPs.setLong(1, recipeId);
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<RecipeIngredient> ingredients = new ArrayList<>();

                while (resultSet.next()) {
                    RecipeIngredient ingredient = mapResultSetToFridge(resultSet);
                    ingredients.add(ingredient);
                }
                return ingredients;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve ingredients for recipe with ID = " + recipeId + ": " + e.getMessage());
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
    public RecipeIngredient update(RecipeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement updatePs = null;

            try {
                updatePs = connection.prepareStatement("UPDATE recipe_ingredients SET recipe_id = ?, ingredient_id = ?, quantity = ? WHERE id = ?");
                updatePs.setLong(1, ingredient.getRecipeId());
                updatePs.setLong(2, ingredient.getIngredientId());
                updatePs.setDouble(3, ingredient.getQuantity());
                updatePs.setLong(4, ingredient.getId());
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
    public boolean delete(RecipeIngredient ingredient) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from recipe_ingredients WHERE id = ?;");
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

    @Override
    public boolean deleteByRecipeId(long recipeId) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from recipe_ingredients WHERE recipe_id = ?;");
                insertionPs.setLong(1, recipeId);
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete ingredients for recipe with ID = " + recipeId + ": " + e.getMessage());
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
