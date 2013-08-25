package com.checkers.server.services.referee.graph;

import com.checkers.server.services.referee.graph.coords.Coords;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Cell <C extends Coords> {

    private C      coords;
    private Figure figure;

    private Cell leftUp;
    private Cell leftDown;
    private Cell rightUp;
    private Cell rightDown;

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        this.figure.setCell(this);
    }

    public Cell getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Cell leftUp) {
        this.leftUp = leftUp;
    }

    public Cell getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Cell leftDown) {
        this.leftDown = leftDown;
    }

    public Cell getRightUp() {
        return rightUp;
    }

    public void setRightUp(Cell rightUp) {
        this.rightUp = rightUp;
    }

    public Cell getRightDown() {
        return rightDown;
    }

    public void setRightDown(Cell rightDown) {
        this.rightDown = rightDown;
    }

    public C getCoords() {
        return coords;
    }

    public void setCoords(C coords) {
        this.coords = coords;
    }
}
