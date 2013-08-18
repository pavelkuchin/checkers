package com.checkers.server.services.referee.graph.coords;

import com.checkers.server.exceptions.CheckersException;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class WorldwideCoords implements Coords{
    private Integer x;
    private Integer y;

    @Override
    public String getCheckersNotation() {
        return null;
    }

    @Override
    public void setCheckersNotation(String checkersNotation) throws CheckersException {

    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }
}
