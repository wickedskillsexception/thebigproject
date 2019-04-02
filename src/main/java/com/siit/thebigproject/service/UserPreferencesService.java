//package com.siit.thebigproject.service;
//
//import com.siit.thebigproject.dao.sql.SQLUsersDAO;
//import com.siit.thebigproject.db.DbException;
//import com.siit.thebigproject.domain.User;
//import com.siit.thebigproject.exceptions.ValidationException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.Collection;
//
//@Service
//public class UserPreferencesService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(com.siit.thebigproject.service.UserService.class);
//
//    @Autowired
//    private SQLUserPreferencesDAO usersDAO;
//
//    public Collection<User> listAll() {
//        try {
//            return usersDAO.getAll();
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public boolean delete(Long id) {
//        LOGGER.debug("Deleting templates for id: " + id);
//        User usr = null;
//        try {
//            usr = usersDAO.getById(id);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (usr != null) {
//            try {
//                usersDAO.delete(usr);
//            } catch (DbException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//
//        return false;
//    }
//
//    public User get(Long id) {
//
//        LOGGER.debug("Getting templates for id: " + id);
//        try {
//            return usersDAO.getById(id);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void save(User user) throws ValidationException {
//        LOGGER.debug("Saving: " + user);
////        validate(User);
//
//        try {
//            usersDAO.update(user);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public SQLUsersDAO getUserDAO() {
//
//        return usersDAO;
//    }
//
//    public void setUserDAO(SQLUsersDAO userDAO) {
//
//        this.usersDAO = userDAO;
//    }
//}
