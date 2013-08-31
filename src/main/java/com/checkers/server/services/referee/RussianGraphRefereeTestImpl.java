package com.checkers.server.services.referee;

import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.services.referee.graph.*;
import com.checkers.server.services.referee.graph.coords.RussianCoords;

import java.util.HashMap;

/**
 * Just for creating board without figures for test purposes
 *
 * @author Pavel Kuhin
 */
public class RussianGraphRefereeTestImpl extends RussianGraphRefereeImpl  {
    public RussianGraphRefereeTestImpl() throws CheckersException {
        Cell<RussianCoords> cell;
        Figure figure;

        graph = new CheckersGraph<RussianCoords>();
        white = new HashMap<RussianCoords, Figure>();
        black = new HashMap<RussianCoords, Figure>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                if ((i + 1) % 2 == 0) {
                    cell = graph.newCell(new RussianCoords(i + 1, 2 + (j * 2)));

                    if(i > 0){
                        Cell c1 = graph.getCell(new RussianCoords(i, 1 + (j * 2)));
                        cell.setLeftDown(c1);
                        c1.setRightUp(cell);
                        if(j < 3){
                            Cell c2 = graph.getCell(new RussianCoords(i, 3 + (j * 2)));
                            cell.setRightDown(c2);
                            c2.setLeftUp(cell);
                        }
                    }
                } else {
                    cell = graph.newCell(new RussianCoords(i + 1, 1 + (j * 2)));

                    if(i > 0){
                        if(j > 0){
                            Cell c3 = graph.getCell(new RussianCoords(i, 0 + (j * 2)));
                            cell.setLeftDown(c3);
                            c3.setRightUp(cell);
                        }
                        Cell c4 = graph.getCell(new RussianCoords(i, 2 + (j * 2)));
                        cell.setRightDown(c4);
                        c4.setLeftUp(cell);
                    }
                }
            }
        }

    }
}
