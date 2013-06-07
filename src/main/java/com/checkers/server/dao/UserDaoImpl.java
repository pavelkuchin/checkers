package com.checkers.server.dao;

import com.checkers.server.beans.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Repository ("userDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDaoImpl implements UserDao {

    static Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void newUser(User user) {
        try{
            em.persist(user);
        }catch (Exception e){
            //Catch any exception
            log.error("newUser: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUser(Long uuid) {
        User user = null;

        try{
            user = em.find(User.class, uuid);
        }catch (Exception e){
            //Catch any exception
            log.error("getUser: " + e.getMessage(), e);
        }

            return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = null;

        try{
            em.createQuery("SELECT u FROM User u").getResultList();
        }catch (Exception e){
            //Catch any exception
            log.error("getUsers: " + e.getMessage(), e);
        }

            return users;
    }
}
