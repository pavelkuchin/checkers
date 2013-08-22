package com.checkers.server.services.referee;

import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.exceptions.LogicException;
import com.checkers.server.services.referee.graph.*;
import com.checkers.server.services.referee.graph.coords.RussianCoords;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class RussianGraphRefereeImpl implements Referee {

    CheckersGraph<RussianCoords> graph;
    List<Figure> white;
    List<Figure> black;

    public RussianGraphRefereeImpl() throws CheckersException {
        Cell    cell;
        Figure  figure;

        white = new LinkedList<Figure>();
        black = new LinkedList<Figure>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                if ((j + 1) % 2 == 0) {
                    cell = graph.newCell(new RussianCoords(i + 1, 2 + (j * 2)));

                    if(i > 0){
                        cell.setLeftDown(graph.getCell(new RussianCoords(i, 1 + (j * 2))));
                        if(j < 3){
                            cell.setRightDown(graph.getCell(new RussianCoords(i, 3 + (j * 2))));
                        }
                    }
                } else {
                    cell = graph.newCell(new RussianCoords(i + 1, 1 + (j * 2)));

                    if(i > 0){
                        if(j > 0){
                            cell.setLeftDown(graph.getCell(new RussianCoords(i, 0 + (j * 2))));
                        }
                        cell.setRightDown(graph.getCell(new RussianCoords(i, 2 + (j * 2))));
                    }
                }

                if(i < 3){
                    figure = new Figure();
                    figure.setType(FigureType.CHECKER);
                    figure.setColor(FigureColor.WHITE);

                    cell.setFigure(figure);
                    white.add(figure);
                }else if(i > 4){
                    figure = new Figure();
                    figure.setType(FigureType.CHECKER);
                    figure.setColor(FigureColor.BLACK);

                    cell.setFigure(figure);
                    black.add(figure);
                }


            }
        }
    }


    private Boolean canCheckerFight(Figure figure){
        return false;
    }

    private Boolean canKingFight(Figure figure){
        return false;
    }

    private Boolean canFigureFigth(Figure figure){
        if(figure.getType() == FigureType.CHECKER){
            return this.canCheckerFight(figure);
        } else if(figure.getType() == FigureType.KING){
            return this.canKingFight(figure);
        }

            return false;
    }

    private RussianCoords checkFightAnywhere(FigureColor color){

        if(color == FigureColor.WHITE){
            for(Figure f : white){

            }
        } else if(color == FigureColor.BLACK) {
            for(Figure f : black){

            }
        }

        return null;
    }

    @Override
    public Boolean checkStep(String step, FigureColor color) throws CheckersException {

        String[] steps = step.split("-|:");

        // Just a step
        if(step.contains("-")){
            for(int i = 0; i < steps.length; i++){
                RussianCoords fromCoord = new RussianCoords(steps[0]);
                RussianCoords toCoord = new RussianCoords(steps[1]);

                //Check step direction (only forward allowed)
                if(toCoord.getX() < fromCoord.getX()){
                    throw new CheckersException(6L, "Checker can't go backward");
                }
            }
        // Step with fight
        } else if(step.contains(":")){
            for(int i = 0; i < steps.length; i++){

            }
        }

        return null;
    }
}
