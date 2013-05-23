package com.checkers.server.services;

import com.checkers.server.beans.Step;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface StepService {
    Step getStep(Long suid);
    void newStep(Step step);
    Step getLastStep();
}
