package com.checkers.server.controllers;

import com.checkers.server.beans.ExceptionMessage;
import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.LogicException;
import com.checkers.server.services.StepService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 *
 * @author Pavel_Kuchin
 */

@Controller
@RequestMapping(value="/steps")
public class StepController {

    static Logger log = Logger.getLogger(StepController.class.getName());

    @Autowired
    StepService stepService;

    /**
     * <h3>/steps/{suid}</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> returns the appropriate step details
     * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
     */
    @RequestMapping(value="/{suid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getStep(@PathVariable String suid) throws Exception {
        log.info("Step with SUID: " + suid + " returned");

        Step step = null;
        Long lSuid;

        try{
            lSuid = Long.parseLong(suid);
        } catch(Exception e){
            throw new LogicException(6L, "{suid} should be a positive number");
        }

        step = stepService.getStep(lSuid);

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
