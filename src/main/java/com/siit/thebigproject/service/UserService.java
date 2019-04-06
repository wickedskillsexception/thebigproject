package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLUsersDAO;
import com.siit.thebigproject.domain.User;
import com.siit.thebigproject.exceptions.ValidationException;
import org.h2.message.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SQLUsersDAO usersDAO;

    public Collection<User> listAll() {
        try {
            return usersDAO.getAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting templates for id: " + id);
        User usr = null;
        try {
            usr = usersDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (usr != null) {
            try {
                usersDAO.delete(usr);
            } catch (DbException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    public User get(Long id) {

        LOGGER.debug("Getting templates for id: " + id);
        try {
            return usersDAO.getById(id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(User user) throws ValidationException {
        LOGGER.debug("Saving: " + user);
        List<String> errors = new LinkedList<>();

        if (StringUtils.isEmpty(user.getUsername())) {
            errors.add("Username is Empty");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.add("User password is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }


        try {
            usersDAO.update(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public SQLUsersDAO getUsersDAO() {

        return usersDAO;
    }

    public void setUsersDAO(SQLUsersDAO usersDAO) {

        this.usersDAO = usersDAO;
    }
}
