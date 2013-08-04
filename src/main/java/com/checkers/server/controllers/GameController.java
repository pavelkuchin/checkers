package com.checkers.server.controllers;

import com.checkers.server.beans.ExceptionMessage;
import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;

import com.checkers.server.exceptions.LogicException;
import com.checkers.server.services.GameService;
import com.checkers.server.services.StepService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;
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
   @RequestMapping(value="", params = {"field", "value"}, method = RequestMethod.GET, headers = {"Accept=application/json"})
   public @ResponseBody
   List<Game> getGamesFiltered(@RequestParam(value = "field") String field, @RequestParam(value = "value") String value){
       log.info("Game filtering started");

       return gameService.getGamesFiltered(field, value);
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
   Game getGame(@PathVariable String gauid) throws LogicException {
       log.info("Game with GAUID: " + gauid + " returned");

       Long lGauid;

       try{
            lGauid = Long.parseLong(gauid);
       }catch(Exception e){
           throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
       }

       return gameService.getGame(lGauid);
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
   Game newGame(@Valid @RequestBody Game game) throws LogicException {
       log.info("Game: \"" + game.getName() + "\" created");
       Game persistGame = gameService.newGame(game);
       return getGame(persistGame.getGauid().toString());
   }

    /**
     * <h3>/games/{gauid}?action=join</h3>
     *
     * <b>Method:</b> PUT
     * <b>Description:</b> join to game
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value="/{gauid}", params = "action=join", method = RequestMethod.PUT, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Game joinGame(@PathVariable String gauid) throws LogicException {
        log.info("Join to game: \"" + gauid + "\"");

        Long lGauid;

        try{
            lGauid = Long.parseLong(gauid);
        } catch(Exception e){
            throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
        }

        return gameService.joinGame(lGauid);
    }

    /**
     * <h3>/games/{gauid}?action=close</h3>
     *
     * <b>Method:</b> PUT
     * <b>Description:</b> close a game or remove it in case if game has open status.
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value="/{gauid}", params = "action=close", method = RequestMethod.PUT, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Game closeGame(@PathVariable String gauid) throws Exception {
        log.info("Game close process initiated for: \"" + gauid + "\"");

        Game game = null;
        Long lGauid;

        try{
            lGauid = Long.parseLong(gauid);
        } catch(Exception e){
            throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
        }

        game = gameService.closeGame(lGauid);

        return game;
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
   Game modGame(@PathVariable String gauid, @Valid @RequestBody Game game) throws Exception {
       log.info("Game modifying has been started");

       Game result;
       Long lGauid;

       try{
           lGauid = Long.parseLong(gauid);
       } catch(Exception e){
           throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
       }

       result = gameService.modGame(lGauid, game);

       return result;
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
   List<Step> getGameSteps(@PathVariable String gauid) throws Exception {
       log.info("All steps for game: " + gauid + " returned");
       List<Step> steps = null;
       Long lGauid;

       try{
           lGauid = Long.parseLong(gauid);
       } catch(Exception e){
           throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
       }

       steps = stepService.getGameSteps(lGauid);

       return steps;
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
   Step newGame(@Valid @RequestBody Step step, @PathVariable final String gauid) throws Exception {
       log.info("Making step");

       Step returnStep;
       Long lGauid;

       try{
           lGauid = Long.parseLong(gauid);
       } catch(Exception e){
           throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
       }

       step.setGauid(lGauid);
       stepService.newStep(step);
       returnStep = stepService.getStep(step.getSuid());

       return returnStep;
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
    Callable<Step> getGameLastStepAsync(@PathVariable final String gauid) throws LogicException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = auth.getName();
        final Long lGauid;

        try{
            lGauid = Long.parseLong(gauid);
        } catch (Exception e){
            throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
        }

        return new Callable<Step>() {
            @Override
            public Step call() throws Exception {
                return stepService.getAsyncGameLastStep(lGauid, username);
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
    Step getGameLastStep(@PathVariable String gauid) throws Exception {
        log.info("Last step for game: " + gauid + " returned");

        Step step;
        Long lGauid;

        try{
            lGauid = Long.parseLong(gauid);
        } catch(Exception e){
            throw new LogicException(6L, "{gauid} should be id of game (positive number type)");
        }

        step = stepService.getGameLastStep(lGauid);

        return step;
    }

    /**
     *
     * EXCEPTION HANDLERS
     *
     */

    @ExceptionHandler(LogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionMessage handleException(LogicException e){
        log.warn(e + " : " + e.getMessage());

        return e.getExceptionMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.warn(e + " : " + e.getMessage());

        ExceptionMessage em = new ExceptionMessage();

        em.setCode(105L);
        em.setMessage(e.getMessage());
        em.setDetailsURL("https://github.com/pavelkuchin/checkers/wiki/Errors#code-105");

        return em;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionMessage ArgumentNotValidException(MethodArgumentNotValidException e){
        log.warn(e + " : " + e.getMessage());

        ExceptionMessage em = new ExceptionMessage();

        em.setCode(106L);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder strErrors = new StringBuilder();
        for(ObjectError oe : errors){
            strErrors.append(oe.getDefaultMessage());
            strErrors.append("\n");
        }

        em.setMessage(strErrors.toString());
        em.setDetailsURL("https://github.com/pavelkuchin/checkers/wiki/Errors#code-106");

        return em;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionMessage internalException(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log.error("Message: " + e.getMessage());
        log.error("StackTrace: " + sw.toString());

        ExceptionMessage em = new ExceptionMessage();

        em.setCode(4L);
        em.setMessage(e.getMessage());
        em.setDetailsURL("https://github.com/pavelkuchin/checkers/wiki/Errors#code-4");

        return em;
    }
}
