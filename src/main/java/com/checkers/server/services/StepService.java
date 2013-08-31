package com.checkers.server.services;

import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.ApplicationException;
import com.checkers.server.exceptions.CheckersException;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid) throws ApplicationException;
    Step newStep(Step step) throws ApplicationException, CheckersException;

    List<Step> getGameSteps(Long gauid) throws ApplicationException;
    Step getGameLastStep(Long gauid) throws ApplicationException;
    Step getAsyncGameLastStep(Long gauid, String username) throws InterruptedException, ApplicationException;
}
