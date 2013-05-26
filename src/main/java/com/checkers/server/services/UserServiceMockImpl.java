package com.checkers.server.services;

import com.checkers.server.beans.User;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class UserServiceMockImpl implements UserService{

    private List<User> users;

    public UserServiceMockImpl(){
        this.users = new LinkedList<User>();
    }

    @Override
    public void newUser(User user) {
        this.users.add(user);
    }

    @Override
    public User getUser(Long uuid) {
        for(User u : this.users){
            if(u.getUuid().equals(uuid)){
                return u;
            }
        }

        return null;
    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }
}
