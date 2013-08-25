package com.checkers.server.services.referee.graph;

import com.checkers.server.services.referee.graph.coords.Coords;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class CheckersGraph <C extends Coords> {

    Map<C, Cell<C>> cells;
    Map<C, Cell<C>> trCells;
    List<C> differedDel;

    public CheckersGraph(){
        this.cells = new HashMap<C, Cell<C>>();
        this.differedDel = new LinkedList<C>();
    }

    public void startTransaction(){
        this.trCells = new HashMap<C, Cell<C>>(this.cells);
    }

    public void rollbackTransaction(){
        if(this.trCells != null){
            this.cells = this.trCells;
        }
    }

    public void commitTransaction(){
        this.trCells = null;

        for(C coords : this.differedDel){
            this.delFigure(coords);
        }
    }

    public void delFigureDeferred(C coords){
        this.differedDel.add(coords);
    }

    public Cell newCell(C coords){
        Cell cell = new Cell();

        cell.setCoords(coords);

        this.cells.put(coords, cell);

            return cell;
    }

    public Cell getCell(C coords){
        return cells.get(coords);
    }

    public void newFigure(C coords, Figure figure){

        Figure tmpFigure;

        Cell pointer = cells.get(coords).getLeftDown();
        while(pointer != null){
            if((tmpFigure = pointer.getFigure()) != null){
                tmpFigure.setRightUpFigure(figure);
                figure.setLeftDownFigure(tmpFigure);
                break;
            }

            pointer = pointer.getLeftDown();
        }

        pointer = cells.get(coords).getLeftUp();
        while(pointer != null){
            if((tmpFigure = pointer.getFigure()) != null){
                tmpFigure.setRightDownFigure(figure);
                figure.setLeftUpFigure(tmpFigure);
                break;
            }

            pointer = pointer.getLeftUp();
        }

        pointer = cells.get(coords).getRightDown();
        while(pointer != null){
            if((tmpFigure = pointer.getFigure()) != null){
                tmpFigure.setLeftUpFigure(figure);
                figure.setRightDownFigure(tmpFigure);
                break;
            }

            pointer = pointer.getRightDown();
        }

        pointer = cells.get(coords).getRightUp();
        while(pointer != null){
            if((tmpFigure = pointer.getFigure()) != null){
                tmpFigure.setLeftDownFigure(figure);
                figure.setRightUpFigure(tmpFigure);
                break;
            }

            pointer = pointer.getRightUp();
        }

        cells.get(coords).setFigure(figure);
    }

    public void moveFigure(C fromCoords, C toCoords){
        Figure figure = this.getFigure(fromCoords);

        delFigure(fromCoords);
        newFigure(toCoords, figure);
    }

    public void delFigure(C coords){
        Figure figure = cells.get(coords).getFigure();

        Figure ldf = figure.getLeftDownFigure();
        Figure luf = figure.getLeftUpFigure();
        Figure rdf = figure.getRightDownFigure();
        Figure ruf = figure.getRightUpFigure();

        if(ldf != null){
            ldf.setRightUpFigure(ruf);
        }
        if(ruf != null){
            ruf.setLeftDownFigure(ldf);
        }
        if(luf != null){
            luf.setRightDownFigure(rdf);
        }
        if(rdf != null){
            rdf.setLeftUpFigure(luf);
        }

        cells.get(coords).setFigure(null);
    }

    public Figure getFigure(C coords){
        return cells.get(coords).getFigure();
    }
}
