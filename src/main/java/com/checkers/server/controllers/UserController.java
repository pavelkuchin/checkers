package com.checkers.server.controllers;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.services.GameService;
import com.checkers.server.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */

@Controller
@RequestMapping(value="/users")
public class UserController {

    static Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    /**
     * <h3>/users</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> return all users as JSON objects list
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<User> getUsers(){
        log.info("All users returned");
            return userService.getUsers();
    }

    /**
     * <h3>/users</h3>
     *
     * <b>Method:</b> POST
     * <b>Description:</b> creates a new user
     * <b>Allowed roles:</b> ROLE_ADMIN
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User newUser(@RequestBody User user){
        log.info("User: \"" + user.getLogin() + "\" created");
        userService.newUser(user);
        return user;
    }

    /**
     * <3>/users/{uuid}</3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> returns user with specific uuid (Long)
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    User getUser(@PathVariable String uuid){
        log.info("Returned user with uuid: " + uuid);

        Long uuidLong = null;

        if(!uuid.equals("me")){
            uuidLong = Long.parseLong(uuid);
        }

            return userService.getUser(uuidLong);
    }

    /**
     * <h3>/users/{uuid}/games</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> returns all user games
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value = "/{uuid}/games", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Game> getUserGames(@PathVariable String uuid){
        log.info("All games for user " + uuid + " returned");

            Long uuidLong = null;

            if(!uuid.equals("me")){
                uuidLong = Long.parseLong(uuid);
            }

                return gameService.getUserGames(uuidLong);
    }

    /**
     *   <h3>/users?action=registration</h3>
     *
     *   <b>Method:</b> POST
     *   <b>Description:</b> new user registrations
     *   <b>Allowed roles:</b> ANYONE
     */
    @RequestMapping(value = "/registration/", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User regUser(@RequestBody User user){
        log.info("User " + user.getLogin() + " registration has been started");

        // First stage of validation
        //TODO Exceptions alert system
        if(user.getLogin().isEmpty()){
            return null;
        }
        if(user.getEmail().isEmpty()){
            return null;
        }
        if(user.getPassword().isEmpty()){
            return null;
        }

        userService.regUser(user);

        return user;
    }

    /**
    *    <3>/users/{uuid}</3>
    *
    *    <b>Method: DELETE</b>
    *    <b>Description: deletes user with specific uuid (Long)</b>
    *    <b>Allowed roles: ROLE_ADMIN</b>
    */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    User delUser(@PathVariable String uuid){
        log.warn("delUser has not implemented yet");

        Long uuidLong = null;

        if(!uuid.equals("me")){
            uuidLong = Long.parseLong(uuid);
        }

        //TODO implementation
        return null;
    }

    /**
    *    <h3>/users/{uuid}</h3>
    *
    *    <b>Method:</b> PUT
    *    <b>Description:</b> modifies user with specific uuid (Long)
    *    <b>Allowed roles:</b> ROLE_ADMIN(owner only), ROLE_ADMIN
    */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT, headers = {"Accept=application/json"})
    public @ResponseBody
    User modUser(@PathVariable String uuid, @RequestBody User user){
        log.info("modUser has not implemented yet");

        Long uuidLong = null;

        if(!uuid.equals("me")){
            uuidLong = Long.parseLong(uuid);
        }

        //TODO implementation
        return null;
    }
}
