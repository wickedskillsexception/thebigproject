//package com.siit.thebigproject.service;
//
//import com.siit.thebigproject.dao.sql.SQLFridgeIngredientDAO;
//import com.siit.thebigproject.dao.sql.SQLFridgesDAO;
//import com.siit.thebigproject.db.DbException;
//import com.siit.thebigproject.domain.Fridge;
//import com.siit.thebigproject.domain.FridgeIngredient;
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
//public class FridgeIngredientService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeIngredientService.class);
//
//    @Autowired
//    private SQLFridgeIngredientDAO fridgeIngredientDAO;
//
//    public Collection<FridgeIngredient> listAll() {
//
//        try {
//            return fridgeIngredientDAO.getAll();
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
//        FridgeIngredient frd = null;
//        try {
//            frd = fridgeIngredientDAO.getById(id);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (frd != null) {
//            try {
//                fridgeIngredientDAO.delete(frd);
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
//    public FridgeIngredient get(Long id) {
//
//        LOGGER.debug("Getting templates for id: " + id);
//        try {
//            return fridgeIngredientDAO.getById(id);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void save(FridgeIngredient fridgeIngredient) throws ValidationException {
//        LOGGER.debug("Saving: " + fridgeIngredient);
////        validate(User);
//
//        try {
//            fridgeIngredientDAO.update(fridgeIngredient);
//        } catch (DbException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public SQLFridgeIngredientDAO getFridgeIngredientDAO() {
//
//        return fridgeIngredientDAO;
//    }
//
//    public void SQLFridgeIngredientDAO(SQLFridgeIngredientDAO fridgeIngredientDAO) {
//
//        this.fridgeIngredientDAO = fridgeIngredientDAO;
//    }
//}
