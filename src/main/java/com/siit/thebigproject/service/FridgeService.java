package com.siit.thebigproject.service;

import com.siit.mvc.FridgeIngredientController;
import com.siit.thebigproject.dao.sql.SQLFridgesDAO;
import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class FridgeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeService.class);

    @Autowired
    private SQLFridgesDAO fridgesDAO;
    public Collection<Fridge> listAll() {

        try {
            return fridgesDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        Fridge frd = null;
        try {
            frd = fridgesDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (frd != null) {
            try {
                fridgesDAO.delete(frd);
            } catch (DbException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public Fridge get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return fridgesDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Fridge fridge) throws ValidationException {
        LOGGER.debug("Saving: " + fridge);
//        validate(User);

        try {
            fridgesDAO.update(fridge);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLFridgesDAO getFridgesDAO() {

        return fridgesDAO;
    }

    public void setFridgesDAO(SQLFridgesDAO fridgesDAO) {

        this.fridgesDAO = fridgesDAO;
    }
}
