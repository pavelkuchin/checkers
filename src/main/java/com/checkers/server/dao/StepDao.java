package com.checkers.server.dao;

import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface StepDao {
    Step getStep(Long suid) throws ApplicationException;
    void newStep(Step step);

    List<Step> getGameSteps(Long gauid);
    Step getGameLastStep(Long gauid) throws ApplicationException;
}
