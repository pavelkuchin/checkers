package com.checkers.server.controllers;

import com.checkers.server.beans.Step;
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
     * <h3>/steps</h3>
     *
     * <b>Method:</b> POST
     * <b>Description:</b> creates a new step
     * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
     */
    @RequestMapping(value="", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Step newGame(@RequestBody Step step){
        log.info("Step: \"" + step.getStep() + "\" created");
        stepService.newStep(step);
        return stepService.getStep(step.getSuid());
    }

    /**
     * <h3>/steps/{suid}</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> returns the appropriate step details
     * <b>Allowed roles:</b> ROLE_USER(game members only), ROLE_ADMIN
     */
    @RequestMapping(value="/{suid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getStep(@PathVariable String suid){
        log.info("Step with SUID: " + suid + " returned");
        return stepService.getStep(Long.parseLong(suid));
    }
}
