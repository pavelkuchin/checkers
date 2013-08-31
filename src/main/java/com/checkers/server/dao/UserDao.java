package com.checkers.server.dao;

import com.checkers.server.beans.User;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserDao {
    void newUser(User user);

    User getUser(Long uuid) throws ApplicationException;

    void delUser(Long uuid) throws ApplicationException;

    User modUser(User user) throws ApplicationException;

    User getUserByLogin(String login) throws ApplicationException;

    List<User> getUsers();
}
