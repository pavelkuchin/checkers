package com.checkers.server.services;

import com.checkers.server.beans.Step;
import com.checkers.server.beans.proxy.StepProxy;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid);
    Step newStep(StepProxy stepProxy);

    List<Step> getGameSteps(Long gauid);
    Step getGameLastStep(Long gauid);
    Step getAsyncGameLastStep(Long gauid, String username) throws InterruptedException;
}
