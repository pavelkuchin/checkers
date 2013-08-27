package com.checkers.server.services.referee.graph;

import com.checkers.server.services.referee.graph.coords.Coords;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Figure<C extends Coords>{
    private FigureType type;
    private FigureColor color;

    private Cell<C>    cell;

    private Boolean threatened  = false;
    private Boolean fighter     = false;

    private Figure leftUpFigure;
    private Figure leftDownFigure;
    private Figure rightUpFigure;
    private Figure rightDownFigure;

    public Figure getLeftUpFigure() {
        return leftUpFigure;
    }

    public void setLeftUpFigure(Figure leftUpFigure) {
        this.leftUpFigure = leftUpFigure;
    }

    public Figure getLeftDownFigure() {
        return leftDownFigure;
    }

    public void setLeftDownFigure(Figure leftDownFigure) {
        this.leftDownFigure = leftDownFigure;
    }

    public Figure getRightUpFigure() {
        return rightUpFigure;
    }

    public void setRightUpFigure(Figure rightUpFigure) {
        this.rightUpFigure = rightUpFigure;
    }

    public Figure getRightDownFigure() {
        return rightDownFigure;
    }

    public void setRightDownFigure(Figure rightDownFigure) {
        this.rightDownFigure = rightDownFigure;
    }

    public Figure getFigureFromDirection(C to){
        Integer dX = 0;
        Integer dY = 0;

        C from = this.cell.getCoords();

        dX = to.getX() - from.getX();
        dY = to.getY() - from.getY();

        Figure tmpFigure = null;
        Integer tmpX = 0;
        Integer tmpY = 0;

        if(dX > 0 && dY > 0){
            tmpFigure = this.getRightUpFigure();

            tmpX = tmpFigure.getCell().getCoords().getX();
            tmpY = tmpFigure.getCell().getCoords().getY();

            if(tmpX <= dX && tmpY <= dY){
                return this.getRightUpFigure();
            } else{
                return null;
            }
        }
        if(dX < 0 && dY < 0){
            tmpFigure = this.getLeftDownFigure();

            tmpX = tmpFigure.getCell().getCoords().getX();
            tmpY = tmpFigure.getCell().getCoords().getY();

            if(tmpX >= dX && tmpY >= dY){
                return this.getLeftDownFigure();
            } else{
                return null;
            }
        }
        if(dX > 0 && dY < 0){
            tmpFigure = this.getLeftUpFigure();

            tmpX = tmpFigure.getCell().getCoords().getX();
            tmpY = tmpFigure.getCell().getCoords().getY();

            if(tmpX <= dX && tmpY >= dY){
                return this.getLeftUpFigure();
            } else{
                return null;
            }
        }
        if(dX < 0 && dY > 0){
            tmpFigure = this.getRightDownFigure();

            tmpX = tmpFigure.getCell().getCoords().getX();
            tmpY = tmpFigure.getCell().getCoords().getY();

            if(tmpX >= dX && tmpY <= dY){
                return this.getRightDownFigure();
            } else{
                return null;
            }
        }

        return null;
    }

    public FigureType getType() {
        return type;
    }

    public void setType(FigureType type) {
        this.type = type;
    }

    public FigureColor getColor() {
        return color;
    }

    public void setColor(FigureColor color) {
        this.color = color;
    }

    public Boolean getThreatened() {
        return threatened;
    }

    public void setThreatened(Boolean threatened) {
        this.threatened = threatened;
    }

    public Cell<C> getCell() {
        return cell;
    }

    public void setCell(Cell<C> cell) {
        this.cell = cell;
    }

    public Boolean getFighter() {
        return fighter;
    }

    public void setFighter(Boolean fighter) {
        this.fighter = fighter;
    }
}
