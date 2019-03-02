package com.siit.thebigproject.service;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.User;
import com.siit.thebigproject.dao.sql.SQLUsersDAO;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SQLUsersDAO usersDAO;

    public Collection <User> listAll() throws DbException, SQLException {
        return usersDAO.getAll();
    }


    public boolean delete(Long id) throws DbException, SQLException {
        LOGGER.debug("Deleting templates for id: " + id);
        User usr = usersDAO.getById(id);
        if (usr != null) {
            usersDAO.delete(usr);
            return true;
        }

        return false;
    }

    public User get(Long id) throws DbException, SQLException {

        LOGGER.debug("Getting templates for id: " + id);
        return usersDAO.getById(id);

    }

    public void save(User user) throws ValidationException, DbException, SQLException {
        LOGGER.debug("Saving: " + user);
//        validate(User);

        usersDAO.update(user);
    }

    public SQLUsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(SQLUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }
}

