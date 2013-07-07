package com.checkers.server.dao;

import com.checkers.server.beans.Step;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface StepDao {
    Step getStep(Long suid);
    void newStep(Step step);

    List<Step> getGameSteps(Long gauid);
    Step getGameLastStep(Long gauid);
}
