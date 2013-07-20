package com.checkers.server.services;

import com.checkers.server.beans.Step;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid) throws Exception;
    Step newStep(Step step) throws Exception;

    List<Step> getGameSteps(Long gauid) throws Exception;
    Step getGameLastStep(Long gauid) throws Exception;
    Step getAsyncGameLastStep(Long gauid, String username) throws InterruptedException, Exception;
}
