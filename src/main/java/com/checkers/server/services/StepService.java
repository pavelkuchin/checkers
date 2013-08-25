package com.checkers.server.services;

import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.exceptions.LogicException;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid) throws LogicException;
    Step newStep(Step step) throws LogicException, CheckersException;

    List<Step> getGameSteps(Long gauid) throws LogicException;
    Step getGameLastStep(Long gauid) throws LogicException;
    Step getAsyncGameLastStep(Long gauid, String username) throws InterruptedException, LogicException;
}
