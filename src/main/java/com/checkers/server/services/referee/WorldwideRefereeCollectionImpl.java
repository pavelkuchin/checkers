package com.checkers.server.services.referee;

import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.ApplicationException;
import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.services.StepService;
import com.checkers.server.services.referee.graph.FigureColor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class WorldwideRefereeCollectionImpl implements RefereeCollection{

    Map<Long, Referee> referees = new HashMap<Long, Referee>();
    StepService stepService;

    public WorldwideRefereeCollectionImpl(StepService stepService) {
        this.stepService = stepService;
    }

    private void prepareReferee(Long gauid) throws CheckersException, ApplicationException {
        Referee referee = new WorldwideGraphRefereeImpl();

        for(Step step : stepService.getGameSteps(gauid)){
            if(step.getGame().getWhite().getUuid().equals(step.getUuid())){
                referee.checkStep(step.getStep(), FigureColor.WHITE);
            } else if(step.getGame().getBlack().getUuid().equals(step.getUuid())){
                referee.checkStep(step.getStep(), FigureColor.BLACK);
            }
        }

        referees.put(gauid, referee);

    }

    @Override
    public synchronized Referee getRefereeByGuid(Long gauid) throws CheckersException, ApplicationException {

        if(referees.containsKey(gauid)){
            return referees.get(gauid);
        } else {
            prepareReferee(gauid);
            return referees.get(gauid);
        }
    }
}
