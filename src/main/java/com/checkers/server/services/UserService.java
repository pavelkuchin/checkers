package com.checkers.server.services;

import com.checkers.server.beans.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserService {
    void newUser(User user);

    void regUser(User user);

    User getUser(Long uuid);

    void delUser(Long uuid);

    User modUser(Long uuid, User user);

    List<User> getUsers();
}
