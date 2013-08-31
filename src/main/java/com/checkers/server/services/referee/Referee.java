package com.checkers.server.services.referee;

import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.services.referee.graph.FigureColor;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public interface Referee {
    public Boolean checkStep(String step, FigureColor color) throws CheckersException;
}
