package com.checkers.server.dao;

import com.checkers.server.beans.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(){

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void newUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUser(Long uuid) {
        return em.find(User.class, uuid);
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }
}
