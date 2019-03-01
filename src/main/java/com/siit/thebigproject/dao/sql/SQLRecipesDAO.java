package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.RecipesDAO;
import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class SQLRecipesDAO extends SQLBaseDAO<Recipe> implements RecipesDAO {

    private ConnectionDb db;

    public SQLRecipesDAO(ConnectionDb db) {
        this.db = db;
    }

    @Override
    public Collection<Recipe> getAll() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from recipes;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<Recipe> recipes = new ArrayList<>();

                while (resultSet.next()) {
                    Recipe recipe = mapResultSetToRecipe(resultSet);
                    recipes.add(recipe);
                }
                return recipes;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all recipes: " + e.getMessage());
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

    private Recipe mapResultSetToRecipe(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setPreparation(resultSet.getString("preparation"));
        recipe.setCalories(resultSet.getInt("calories"));
        return recipe;
    }

    @Override
    public void add(Recipe recipe) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;
            PreparedStatement crtValPs = null;

            try {
                insertionPs = connection.prepareStatement("INSERT INTO recipes(name, preparation, calories) values( ?, ?)");
                insertionPs.setString(1, recipe.getName());
                insertionPs.setString(2, recipe.getPreparation());
                insertionPs.setInt(2, recipe.getCalories());
                insertionPs.executeUpdate();

                crtValPs = connection.prepareStatement("SELECT CURRVAL('recipes_ids')");
                ResultSet resultSet = crtValPs.executeQuery();
                resultSet.next();
                recipe.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                System.err.println("Cannot insert recipe: " + e.getMessage());
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
    public Recipe getById(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from recipes WHERE id = ? ;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();

                if(resultSet.next()){
                    Recipe recipe = mapResultSetToRecipe(resultSet);
                    return recipe;
                }
            }catch (SQLException e) {
                System.err.println("Cannot retrieve recipe with specified user ID: " + e.getMessage());
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
    public Recipe update(Recipe recipe) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement updatePs = null;

            try {
                updatePs = connection.prepareStatement("UPDATE recipes SET name = ?," +
                        " preparation = ?, calories = ? WHERE id = ? ;");
                updatePs.setString(1, recipe.getName());
                updatePs.setString(2, recipe.getPreparation());
                updatePs.setInt(3, recipe.getCalories());
                updatePs.setLong(4, recipe.getId());
                updatePs.executeUpdate();

                return recipe;
            } catch (SQLException e) {
                System.err.println("Cannot update recipe: " + e.getMessage());
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
    public boolean deleteByName(String name) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from recipes WHERE name = ?;");
                insertionPs.setString(1,name);
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete recipe: " + e.getMessage());
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
    public boolean delete(Recipe recipe) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from users WHERE id = ?;");
                insertionPs.setLong(1, recipe.getId());
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete recipe: " + e.getMessage());
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
