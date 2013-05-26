package com.checkers.server.controllers;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.services.GameService;
import com.checkers.server.services.UserService;
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
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User newUser(@RequestBody User user){
        System.out.println("User: \"" + user.getLogin() + "\" created");
        userService.newUser(user);
            return user;
    }

    @RequestMapping(method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<User> getUsers(){
        System.out.println("All users returned");
            return userService.getUsers();
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    User getUser(@PathVariable String uuid){
        System.out.println("Returned user with uuid: " + uuid);
            return userService.getUser(Long.parseLong(uuid));
    }

    @RequestMapping(value = "/{uuid}/game", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Game> getUserGames(@PathVariable String uuid){
        System.out.println("All games for user " + uuid + " returned");
            return gameService.getUserGames(Long.parseLong(uuid));
    }

}
