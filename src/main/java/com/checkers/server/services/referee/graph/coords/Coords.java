package com.checkers.server.services.referee.graph.coords;

import com.checkers.server.exceptions.CheckersException;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public interface Coords {
    public String getCheckersNotation();

    public void setCheckersNotation(String checkersNotation) throws CheckersException;

    public Integer getX();

    public Integer getY();

}
