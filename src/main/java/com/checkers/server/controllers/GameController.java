package com.checkers.server.controllers;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;
import com.checkers.server.beans.Step;

import com.checkers.server.services.GameService;
import com.checkers.server.services.StepService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 *
 * @author Pavel_Kuchin
 */

@Controller
@RequestMapping(value="/game")
public class GameController {

    static Logger log = Logger.getLogger(GameController.class.getName());

    @Autowired
    GameService gameService;

    @Autowired
    StepService stepService;

    @RequestMapping(value="/{gauid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Game getGame(@PathVariable String gauid){
        log.info("Game with GAUID: " + gauid + " returned");
        return gameService.getGame(Long.parseLong(gauid));
    }

    @RequestMapping(value="", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Game> getGames(){
        log.info("All games returned");
        return gameService.getGames();
    }

    @RequestMapping(value="", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Game newGame(@RequestBody GameProxy game){
        log.info("Game: \"" + game.getName() + "\" created");
        Game persistGame = gameService.newGame(game);
        return getGame(persistGame.getGauid().toString());
    }

    @RequestMapping(value="/{gauid}/step", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Step> getGameSteps(@PathVariable String gauid){
        log.info("All steps for game: " + gauid + " returned");
        return stepService.getGameSteps(Long.parseLong(gauid));
    }

    @RequestMapping(value="/{gauid}/laststep", params = "mode=async", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Callable<Step> getGameLastStepAsync(@PathVariable final String gauid){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = auth.getName();

        return new Callable<Step>() {
            @Override
            public Step call() throws Exception {
                return stepService.getAsyncGameLastStep(Long.parseLong(gauid), username);
            }
        };
    }

    @RequestMapping(value="/{gauid}/laststep", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getGameLastStep(@PathVariable String gauid){
        log.info("Last step for game: " + gauid + " returned");
        return stepService.getGameLastStep(Long.parseLong(gauid));
    }
}
