package com.checkers.server.services;

import com.checkers.server.beans.User;
import com.checkers.server.exceptions.LogicException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserService {
    void newUser(User user) throws LogicException;

    void regUser(User user) throws LogicException;

    User getUser(Long uuid) throws LogicException;

    void delUser(Long uuid) throws LogicException;

    User modUser(Long uuid, User user) throws LogicException;

    List<User> getUsers();
}
