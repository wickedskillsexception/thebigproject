package com.siit.thebigproject.service;

import com.siit.thebigproject.base.User;
import com.siit.thebigproject.db.sql.IMUserDAO;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IMUserDAO imUserDAO;

    public Collection <User> listAll() {
        return imUserDAO.readAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        User usr = imUserDAO.getById(id);
        if (usr != null) {
            imUserDAO.delete(usr);
            return true;
        }

        return false;
    }

    public User get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        return imUserDAO.getById(id);

    }

    public void save(User user) throws ValidationException {
        LOGGER.debug("Saving: " + user);
//        validate(User);

        imUserDAO.update(user);
    }

    public IMUserDAO getIMUserDAO() {

        return imUserDAO;
    }

    public void setIMUserDAO(IMUserDAO imUserDAO) {

        this.imUserDAO = imUserDAO;
    }
}
