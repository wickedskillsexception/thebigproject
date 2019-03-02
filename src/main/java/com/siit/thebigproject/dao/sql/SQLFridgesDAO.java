package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.FridgesDAO;
import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class SQLFridgesDAO extends SQLBaseDAO<Fridge> implements FridgesDAO {

    @Autowired
    ConnectionDb db = new ConnectionDb();

    @Override
    public void add(Fridge fridge) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;
            PreparedStatement crtValPs = null;

            try {
                insertionPs = connection.prepareStatement("INSERT INTO fridges(user_id) values( ?)");
                insertionPs.setInt(1, fridge.getUserId());
                insertionPs.executeUpdate();

                crtValPs = connection.prepareStatement("SELECT CURRVAL('fridges_ids')");
                ResultSet resultSet = crtValPs.executeQuery();
                resultSet.next();
                fridge.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                System.err.println("Cannot insert Fridge: " + e.getMessage());
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
    public Collection<Fridge> getAll() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from fridges;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<Fridge> fridges = new ArrayList<>();

                while (resultSet.next()) {
                    Fridge fridge = mapResultSetToFridge(resultSet);
                    fridges.add(fridge);
                }
                return fridges;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Fridges: " + e.getMessage());
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

    private Fridge mapResultSetToFridge(ResultSet resultSet) throws SQLException {
        Fridge fridge = new Fridge();
        fridge.setId(resultSet.getInt("id"));
        fridge.setUserId(resultSet.getInt("user_id"));
        return fridge;
    }

    private String mapResultSetToString(ResultSet resultSet) throws SQLException {
        String s = resultSet.getInt("id") + " " + resultSet.getString("username");
        return s;
    }

    @Override
    public Collection<Fridge> getAllWithIngredients(){
        //TODO!!!
        return null;
    }

    @Override
    public Fridge getById(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from fridges WHERE id = ?;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<Fridge> fridges = new ArrayList<>();

                resultSet.next();
                Fridge fridge = mapResultSetToFridge(resultSet);
                return fridge;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Fridges: " + e.getMessage());
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
    public String getByIdWithUserDetails(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT f.id, u.username from fridges f INNER JOIN " +
                        "users u ON f.user_id = u.id WHERE f.id = ?;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();

                resultSet.next();
                String s = mapResultSetToString(resultSet);
                return s;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Fridges: " + e.getMessage());
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
    public StringBuilder getAllWithUserDetails() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT f.id, u.username from fridges f INNER JOIN " +
                        "users u ON f.user_id = u.id ");
                ResultSet resultSet = selectPs.executeQuery();
                StringBuilder s = new StringBuilder();

                while(resultSet.next()) {
                    s.append(mapResultSetToString(resultSet));
                    s.append("\n");
                }
                return s;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Fridges: " + e.getMessage());
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
    public Fridge update(Fridge fridge) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement updatePs = null;

            try {
                updatePs = connection.prepareStatement("UPDATE fridges SET user_id = ? WHERE id = ?");
                updatePs.setInt(1, fridge.getUserId());
                updatePs.setLong(2, fridge.getId());
                updatePs.executeUpdate();

                return fridge;
            } catch (SQLException e) {
                System.err.println("Cannot update Fridge: " + e.getMessage());
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
    public boolean delete(Fridge fridge) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from fridges WHERE id = ?;");
                insertionPs.setLong(1, fridge.getId());
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete Fridge: " + e.getMessage());
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




