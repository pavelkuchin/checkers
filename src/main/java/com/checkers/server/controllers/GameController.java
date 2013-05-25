package com.checkers.server.controllers;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.services.GameService;
import com.checkers.server.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */

@Controller
@RequestMapping(value="/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    StepService stepService;

    @RequestMapping(value="/{gauid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Game getGame(@PathVariable String gauid){
        System.out.println("Game with GAUID: " + gauid + " returned");
        return gameService.getGame(Long.parseLong(gauid));
    }

    @RequestMapping(method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Game> getGames(){
        System.out.println("All games returned");
        return gameService.getGames();
    }

    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Game newGame(@RequestBody Game game){
        System.out.println("Game: \"" + game.getName() + "\" created");
        gameService.newGame(game);
        return game;
    }

    @RequestMapping(value="/{gauid}/step", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Step> getGameSteps(@PathVariable String gauid){
        System.out.println("All steps for game: " + gauid + " returned");
        return stepService.getGameSteps(Long.parseLong(gauid));
    }

    @RequestMapping(value="/{gauid}/laststep", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getGameLastStep(@PathVariable String gauid){
        System.out.println("Last step for game: " + gauid + " returned");
        return stepService.getGameLastStep(Long.parseLong(gauid));
    }
}
