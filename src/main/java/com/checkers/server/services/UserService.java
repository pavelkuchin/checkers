package com.checkers.server.services;

import com.checkers.server.beans.User;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserService {
    void newUser(User user) throws ApplicationException;

    void regUser(User user) throws ApplicationException;

    User getUser(Long uuid) throws ApplicationException;

    User getUserByLogin(String login) throws ApplicationException;

    void delUser(Long uuid) throws ApplicationException;

    User modUser(Long uuid, User user) throws ApplicationException;

    List<User> getUsers();
}
