package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.UsersDAO;
import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class SQLUsersDAO extends SQLBaseDAO<User> implements UsersDAO {

    @Autowired
    private ConnectionDb db;

    public SQLUsersDAO(ConnectionDb db) {
        this.db = db;
    }

    @Override
    public Collection<User> getAll() throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from users;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    User user = mapResultSetToUser(resultSet);
                    users.add(user);
                }
                return users;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Users: " + e.getMessage());
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

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setDesiredCalories(resultSet.getInt("desired_calories"));
        return user;
    }

    @Override
    public void add(User user) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;
            PreparedStatement crtValPs = null;

            try {
                insertionPs = connection.prepareStatement("INSERT INTO users(username, password) values( ?, ?)");
                insertionPs.setString(1, user.getUsername());
                insertionPs.setString(2, user.getPassword());
                insertionPs.executeUpdate();

                crtValPs = connection.prepareStatement("SELECT CURRVAL('users_ids')");
                ResultSet resultSet = crtValPs.executeQuery();
                resultSet.next();
                user.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                System.err.println("Cannot insert User: " + e.getMessage());
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
    public boolean updateUserDetails(String username, String email, int calories) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("UPDATE users SET email = ?, desired_calories = ? " +
                        "WHERE username = ? ;");

                insertionPs.setString(1, email);
                insertionPs.setInt(2, calories);
                insertionPs.setString(3, username);
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot insert User: " + e.getMessage());
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
    public User getById(Long id) throws DbException, SQLException {
        try (Connection conn = db.connectToMyDb()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from users WHERE id = ? ;");
                selectPs.setLong(1, id);
                ResultSet resultSet = selectPs.executeQuery();

                if(resultSet.next()){
                    User user = mapResultSetToUser(resultSet);
                    return user;
                }
            }catch (SQLException e) {
                System.err.println("Cannot retrieve user with specified user ID: " + e.getMessage());
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
    public User update(User user) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement updatePs = null;

            try {
                updatePs = connection.prepareStatement("UPDATE users SET username = ?," +
                        " password = ?, email = ?, desired_calories = ? WHERE id = ? ;");
                updatePs.setString(1, user.getUsername());
                updatePs.setString(2, user.getPassword());
                updatePs.setString(3, user.getEmail());
                updatePs.setInt(4, user.getDesiredCalories());
                updatePs.setLong(5, user.getId());
                updatePs.executeUpdate();

                return user;
            } catch (SQLException e) {
                System.err.println("Cannot update User: " + e.getMessage());
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
    public boolean deleteByUsername(String username) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from users WHERE username = ?;");
                insertionPs.setString(1,username);
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete User: " + e.getMessage());
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
    public boolean delete(User user) throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            PreparedStatement insertionPs = null;

            try {
                insertionPs = connection.prepareStatement("DELETE from users WHERE id = ?;");
                insertionPs.setLong(1, user.getId());
                insertionPs.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Cannot delete User: " + e.getMessage());
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

