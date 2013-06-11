package com.checkers.server.controllers;

import com.checkers.server.beans.Step;
import com.checkers.server.beans.proxy.StepProxy;
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
@RequestMapping(value="/step")
public class StepController {

    static Logger log = Logger.getLogger(StepController.class.getName());

    @Autowired
    StepService stepService;

    @RequestMapping(value="/{suid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getStep(@PathVariable String suid){
        log.info("Step with SUID: " + suid + " returned");
        return stepService.getStep(Long.parseLong(suid));
    }

    @RequestMapping(value="", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Step newGame(@RequestBody StepProxy stepProxy){
        log.info("Step: \"" + stepProxy.getStep() + "\" created");
        Step step = stepService.newStep(stepProxy);
        return getStep(step.getSuid().toString());
    }

}
