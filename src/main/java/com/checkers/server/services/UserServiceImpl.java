package com.checkers.server.services;

import com.checkers.server.Consts;
import com.checkers.server.beans.User;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void newUser(User user) {

        user.setUuid(null);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);
    }

    @Override
    public void regUser(User user) {

        // Fields autofill
        user.setUuid(null);

        //TODO User should be enabled only after email verification
        user.setEnabled(true);

        user.setRole("ROLE_USER");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public User getUser(Long uuid) {

        if(uuid == null){
            //TODO it is bad solution but i have not any other now.
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            uuid = userDao.getUserByLogin(name).getUuid();
        }

        User user = userDao.getUser(uuid);

            return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delUser(Long uuid) {
        userDao.delUser(uuid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public User modUser(Long uuid, User user) {
        if(uuid == null){
            //TODO it is bad solution but i have not any other now.
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            uuid = userDao.getUserByLogin(name).getUuid();
        }

        User origin = userDao.getUser(uuid);

        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if(authorities.contains(Consts.ROLE_ADMIN)){
            if(user.getPassword() != null){
                origin.setPassword(user.getPassword());
            }

            if(user.getEnabled() != null){
                origin.setEnabled(user.getEnabled());
            }

            if(user.getRole() != null){
                origin.setRole(user.getRole());
            }

            if(user.getFirstName() != null){
                origin.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null){
                origin.setLastName(user.getLastName());
            }
            if(user.getEmail() != null){
                origin.setEmail(user.getEmail());
            }

        } else if(authorities.contains(Consts.ROLE_USER)){
            //TODO password should be changed in other request with old password verification
            if(user.getPassword() != null){
                origin.setPassword(user.getPassword());
            }

            if(user.getFirstName() != null){
                origin.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null){
                origin.setLastName(user.getLastName());
            }

            //TODO email should be changed in other request with email verification
            if(user.getEmail() != null){
                origin.setEmail(user.getEmail());
            }

        }

        origin.setModified(new Date());

        userDao.modUser(origin);

        return userDao.getUser(uuid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
