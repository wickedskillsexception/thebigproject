package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class SQLIngredientsDAO extends SQLBaseDAO<Ingredient> implements BaseDAO<Ingredient> {

        private ConnectionDb db;

        @Override
        public Collection<Ingredient> getAll() throws DbException, SQLException {
            try (Connection conn = db.connectToMyDb()) {
                PreparedStatement selectPs = null;

                try {
                    selectPs = conn.prepareStatement("SELECT * from ingredients;");
                    ResultSet resultSet = selectPs.executeQuery();
                    ArrayList<Ingredient> ingredients = new ArrayList<>();

                    while (resultSet.next()) {
                        Ingredient ingredient = mapResultSetToIngredient(resultSet);
                        ingredients.add(ingredient);
                    }
                    return ingredients;
                }catch (SQLException e) {
                    System.err.println("Cannot retrieve all Ingredients: " + e.getMessage());
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

        private Ingredient mapResultSetToIngredient(ResultSet resultSet) throws SQLException {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt("id"));
            ingredient.setName(resultSet.getString("name"));
            ingredient.setUnit(resultSet.getString("measurement_unit"));

            return ingredient;
        }

        @Override
        public void add(Ingredient ingredient) throws DbException, SQLException {
            try (Connection connection = db.connectToMyDb()) {
                PreparedStatement insertionPs = null;
                PreparedStatement crtValPs = null;

                try {
                    insertionPs = connection.prepareStatement("INSERT INTO ingredients(name, measurement_unit)");
                    insertionPs.setString(1, ingredient.getName());
                    insertionPs.setString(2, ingredient.getUnit());
                    insertionPs.executeUpdate();

                    crtValPs = connection.prepareStatement("SELECT CURRVAL('ingredients_ids')");
                    ResultSet resultSet = crtValPs.executeQuery();
                    resultSet.next();
                    ingredient.setId(resultSet.getInt(1));
                } catch (SQLException e) {
                    System.err.println("Cannot insert Ingredient: " + e.getMessage());
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
        public Ingredient getById(Long id) throws DbException, SQLException {
            try (Connection conn = db.connectToMyDb()) {
                PreparedStatement selectPs = null;

                try {
                    selectPs = conn.prepareStatement("SELECT * from ingredients WHERE id = ? ;");
                    selectPs.setLong(1, id);
                    ResultSet resultSet = selectPs.executeQuery();

                    if(resultSet.next()){
                        Ingredient ingredient = mapResultSetToIngredient(resultSet);
                        return ingredient;
                    }
                }catch (SQLException e) {
                    System.err.println("Cannot retrieve ingredient with specified ingredient ID: " + e.getMessage());
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
        public Ingredient update(Ingredient ingredient) throws DbException, SQLException {
            try (Connection connection = db.connectToMyDb()) {
                PreparedStatement updatePs = null;

                try {
                    updatePs = connection.prepareStatement("UPDATE ingredients SET name = ?," +
                            " measurement_unit = ? WHERE id = ?;");
                    updatePs.setString(1, ingredient.getName());
                    updatePs.setString(2, ingredient.getUnit());
                    updatePs.setLong(3, ingredient.getId());
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
        public boolean delete(Ingredient ingredient) throws DbException, SQLException {
            try (Connection connection = db.connectToMyDb()) {
                PreparedStatement insertionPs = null;

                try {
                    insertionPs = connection.prepareStatement("DELETE from ingredients WHERE id = ?;");
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
