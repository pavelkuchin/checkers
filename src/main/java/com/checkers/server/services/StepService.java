package com.checkers.server.services;

import com.checkers.server.beans.Step;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid);
    void newStep(Step step);

    List<Step> getGameSteps(Long gauid);
    Step getGameLastStep(Long gauid);
}
