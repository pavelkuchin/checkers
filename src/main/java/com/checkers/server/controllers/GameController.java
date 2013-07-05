package com.checkers.server.controllers;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;
import com.checkers.server.beans.Step;

import com.checkers.server.beans.proxy.StepProxy;
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
@RequestMapping(value="/games")
public class GameController {

    static Logger log = Logger.getLogger(GameController.class.getName());

    @Autowired
    GameService gameService;

    @Autowired
    StepService stepService;

   /**
    * <h3>/games</h3>
    *
    * <b>Method:</b> GET
    * <b>Description:</b> returns all games
    * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
    */
    @RequestMapping(value="", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Game> getGames(){
        log.info("All games returned");
        return gameService.getGames();
    }

   /**
    * <h3>/games?field={field}&value={value}</h3>
    *
    * <b>Method:</b> GET
    * <b>Description:</b> returns all game with exact filtering on one field. It is userful for getting all open games
    * (field=status&value=open).
    * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
    *
    */
   @RequestMapping(value="", params = {"field", "value"},method = RequestMethod.GET, headers = {"Accept=application/json"})
   public @ResponseBody
   List<Game> getGamesFiltered(@RequestParam(value = "field") String field, @RequestParam(value = "value") String value){
       log.warn("getGamesFiltered has not implemented yet");
       //TODO implementation
       return null;
   }

   /**
    * <h3>/games/{gauid}</h3>
    *
    * <b>Method:</b> GET
    * <b>Description:</b> returns the appropriate game details
    * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
    */
   @RequestMapping(value="/{gauid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
   public @ResponseBody
   Game getGame(@PathVariable String gauid){
       log.info("Game with GAUID: " + gauid + " returned");
       return gameService.getGame(Long.parseLong(gauid));
   }

   /**
    * <h3>/games</h3>
    *
    * <b>Method:</b> POST
    * <b>Description:</b> creates a new game
    * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
    */
   @RequestMapping(value="", method = RequestMethod.POST, headers = {"Accept=application/json"})
   @ResponseStatus(HttpStatus.CREATED)
   public @ResponseBody
   Game newGame(@RequestBody GameProxy game){
       log.info("Game: \"" + game.getName() + "\" created");
       Game persistGame = gameService.newGame(game);
       return getGame(persistGame.getGauid().toString());
   }

   /**
    * <h3>/games/{gauid}</h3>
    *
    * <b>Method:</b> PUT
    * <b>Description:</b> changes game fields
    * <b>Allowed roles:</b> ROLE_USER(owner only), ROLE_ADMIN
    */
   @RequestMapping(value="/{gauid}", method = RequestMethod.PUT, headers = {"Accept=application/json"})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody
   Game modGame(@PathVariable String gauid){
       log.warn("modGame has not implemented yet");
       //TODO implementation
       return null;
   }

   /**
    * <h3>/games/{gauid}/steps</h3>
    *
    * <b>Method:</b> GET
    * <b>Description:</b> returns all steps for the appropriate game
    * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
    */
   @RequestMapping(value="/{gauid}/steps", method = RequestMethod.GET, headers = {"Accept=application/json"})
   public @ResponseBody
   List<Step> getGameSteps(@PathVariable String gauid){
       log.info("All steps for game: " + gauid + " returned");
       return stepService.getGameSteps(Long.parseLong(gauid));
   }

   /**
    * <h3>/games/{gauid}/steps</h3>
    *
    * <b>Method:</b> POST
    * <b>Description:</b> makes step
    * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
    */
   @RequestMapping(value="/{gauid}/steps", method = RequestMethod.POST, headers = {"Accept=application/json"})
   @ResponseStatus(HttpStatus.CREATED)
   public @ResponseBody
   Step newGame(@RequestBody StepProxy stepProxy, @PathVariable final String gauid){
       log.warn("newGameStep has not implemented yet. Please use /steps instead.");
       //TODO implementation
       return null;
   }

   /**
    * <h3>/games/{gauid}/steps?mode=opponentStepAsync</h3>
    *
    * <b>Method:</b> GET
    * <b>Description:</b> gets last opponent step in async mode
    * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
    */
    @RequestMapping(value="/{gauid}/steps", params = "mode=opponentStepAsync", method = RequestMethod.GET, headers = {"Accept=application/json"})
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

    /**
     * <h3>/games/{gauid}/steps?mode=opponentStep</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> gets last opponent step in sync mode
     * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
     */
    @RequestMapping(value="/{gauid}/steps", params = "mode=opponentStep", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getGameLastStep(@PathVariable String gauid){
        log.info("Last step for game: " + gauid + " returned");
        return stepService.getGameLastStep(Long.parseLong(gauid));
    }
}
