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

        step = stepService.getStep(Long.parseLong(suid));

        return step;
    }

    @ExceptionHandler(LogicException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody
    ExceptionMessage handleException(LogicException e){
        log.warn("There is some exception: " + e.getMessage());

        return e.getExceptionMessage();
    }
}
