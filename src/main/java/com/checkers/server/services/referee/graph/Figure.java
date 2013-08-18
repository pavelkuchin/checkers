package com.checkers.server.services.referee.graph;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Figure{
    private FigureType type;
    private FigureColor color;

    private Cell    cell;

    private Boolean threatened;

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

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
