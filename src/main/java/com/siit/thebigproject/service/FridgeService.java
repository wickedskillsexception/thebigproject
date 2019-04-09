package com.siit.thebigproject.service;


import com.siit.thebigproject.dao.sql.SQLFridgesDAO;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class FridgeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeService.class);

    @Autowired
    private SQLFridgesDAO fridgesDAO;

    public Collection<Fridge> listAll() {
        return fridgesDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting myFridge with id: " + id);
        Fridge fridge = null;
        fridge = fridgesDAO.getById(id);

        return fridgesDAO.delete(fridge);
    }

    public Fridge get(Long id) {
        LOGGER.debug("Getting myFridge with id: " + id);

        return fridgesDAO.getById(id);

    }

    public void save(Fridge fridge) throws ValidationException {
        LOGGER.debug("Saving: " + fridge);
        List<String> errors = new LinkedList<>();

        if (StringUtils.isEmpty(fridge.getUserId())) {
            errors.add("Fridge UserId is Empty");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
        fridgesDAO.update(fridge);
    }

    public SQLFridgesDAO getFridgesDAO() {
        return fridgesDAO;
    }

    public void setFridgesDAO(SQLFridgesDAO fridgesDAO) {
        this.fridgesDAO = fridgesDAO;
    }
}
