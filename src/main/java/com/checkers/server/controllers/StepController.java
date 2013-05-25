package com.checkers.server.controllers;

import com.checkers.server.beans.Step;
import com.checkers.server.services.StepService;
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
    @Autowired
    StepService stepService;

    @RequestMapping(value="/{suid}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    Step getStep(@PathVariable String suid){
        System.out.println("Step with SUID: " + suid + " returned");
        return stepService.getStep(Long.parseLong(suid));
    }

    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Step newGame(@RequestBody Step step){
        System.out.println("Step: \"" + step.getStep() + "\" created");
        stepService.newStep(step);
        return step;
    }

}
