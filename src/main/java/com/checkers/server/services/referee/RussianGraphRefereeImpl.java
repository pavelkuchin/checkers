package com.checkers.server.services.referee;

import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.services.referee.graph.*;
import com.checkers.server.services.referee.graph.coords.RussianCoords;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class RussianGraphRefereeImpl implements Referee {

    CheckersGraph<RussianCoords> graph;
    Map<RussianCoords, Figure> white;
    Map<RussianCoords, Figure> black;

    public RussianGraphRefereeImpl() throws CheckersException {
        Cell<RussianCoords> cell;
        Figure  figure;

        graph = new CheckersGraph<RussianCoords>();
        white = new HashMap<RussianCoords, Figure>();
        black = new HashMap<RussianCoords, Figure>();

        for(int i = 0; i < 8; i++){
            Integer offset;
            Integer rOffset;

            if((i + 1) % 2 == 0){
                offset = 2;
                rOffset = 1;
            } else{
                offset = 1;
                rOffset = 2;
            }

            for(int j = 0; j < 4; j++){
                if ((j + 1) % 2 == 0) {
                    cell = graph.newCell(new RussianCoords(i + 1, offset + (j * 2)));

                    if(i > 0){
                        cell.setLeftDown(graph.getCell(new RussianCoords(i, rOffset + (j * 2))));
                        if(j < 3){
                            cell.setRightDown(graph.getCell(new RussianCoords(i, rOffset + (j * 2))));
                        }
                    }
                } else {
                    cell = graph.newCell(new RussianCoords(i + 1, offset + (j * 2)));

                    if(i > 0){
                        if(j > 0){
                            cell.setLeftDown(graph.getCell(new RussianCoords(i, rOffset + (j * 2))));
                        }
                        if(j < 3){
                            cell.setRightDown(graph.getCell(new RussianCoords(i, rOffset + (j * 2))));
                        }
                    }
                }

                if(i < 3){
                    figure = new Figure();
                    figure.setType(FigureType.CHECKER);
                    figure.setColor(FigureColor.WHITE);

                    graph.newFigure(cell.getCoords(), figure);
                    white.put(cell.getCoords(), figure);
                }else if(i > 4){
                    figure = new Figure();
                    figure.setType(FigureType.CHECKER);
                    figure.setColor(FigureColor.BLACK);

                    graph.newFigure(cell.getCoords(), figure);
                    black.put(cell.getCoords(), figure);
                }
            }
        }
    }

    private Integer getDistance(Figure<RussianCoords> from, Figure<RussianCoords> to){
        if(from == null || to == null){
            return null;
        }

        return Math.abs(from.getCell().getCoords().getX() - to.getCell().getCoords().getX());
    }

    private Boolean canCheckerFight(Figure<RussianCoords> figure){

        Figure<RussianCoords> f = null;

        Integer vX = null;
        Integer svX = null;

        f = figure.getLeftDownFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                vX = getDistance(figure, f);

                if(vX == null){
                    return false;
                }

                if(vX.equals(1)){
                    svX = getDistance(f, f.getLeftDownFigure());

                    if(svX == null){
                        return true;
                    }

                    if(!svX.equals(1)){
                        return true;
                    }
                }
            }
        }

        f = figure.getLeftUpFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                vX = getDistance(figure, f);

                if(vX == null){
                    return false;
                }

                if(vX.equals(1)){
                    svX = getDistance(f, f.getLeftUpFigure());

                    if(svX == null){
                        return true;
                    }

                    if(!svX.equals(1)){
                        return true;
                    }
                }
            }
        }

        f = figure.getRightDownFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                vX = getDistance(figure, f);

                if(vX == null){
                    return false;
                }

                if(vX.equals(1)){
                    svX = getDistance(f, f.getRightDownFigure());

                    if(svX == null){
                        return true;
                    }

                    if(!svX.equals(1)){
                        return true;
                    }
                }
            }
        }

        f = figure.getRightUpFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                vX = getDistance(figure, f);

                if(vX == null){
                    return false;
                }

                if(vX.equals(1)){
                    svX = getDistance(f, f.getRightUpFigure());

                    if(svX == null){
                        return true;
                    }

                    if(!svX.equals(1)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private Boolean canKingFightAfterFight(Figure figure){
        Boolean result = canKingFight(figure);

        Cell<RussianCoords> l;
        Cell<RussianCoords> sl1;
        Cell<RussianCoords> sl2;

        if(result == false){
            if(figure.getLeftDownFigure() != null && figure.getLeftDownFigure().getThreatened()){
                // if from left down to right up
                //  then get all right upper cell in the loop
                //      and from every check all right down and left up
                //        if found our figure then out of the loop
                //        if found their figure then return true

                // current position has been checked in the canKingFight function,
                // so we just skip it.
                l = figure.getCell();

                while((l = l.getRightUp()) != null){
                   if(l.getFigure() != null){
                       if(!l.getFigure().getColor().equals(figure.getColor())){
                           return true;
                       } else{
                           return false;
                       }
                   } else{
                       sl1 = l;
                       while((sl1 = sl1.getRightDown()) != null){
                           if(!sl1.getFigure().getColor().equals(figure.getColor())){
                               if(sl1.getRightDown() != null
                                       && sl1.getRightDown().getFigure() == null){
                                   return true;
                               }
                           } else{
                               break;
                           }
                       }

                       sl2 = l;
                       while((sl2 = sl2.getLeftUp()) != null){
                           if(!sl2.getFigure().getColor().equals(figure.getColor())){
                               if(sl2.getLeftUp() != null
                                       && sl2.getLeftUp().getFigure() == null){
                                   return true;
                               }
                           } else{
                               break;
                           }
                       }
                   }
                }
            }
            if(figure.getLeftUpFigure() != null && figure.getLeftUpFigure().getThreatened()){
                // if from left up to right down
                //  then get all right nether cell in the loop
                //      and from every check all right up and left down
                //        if found our figure then out of the loop
                //        if found their figure then return true

                // current position has been checked in the canKingFight function,
                // so we just skip it.
                l = figure.getCell();

                while((l = l.getRightDown()) != null){
                    if(l.getFigure() != null){
                        if(!l.getFigure().getColor().equals(figure.getColor())){
                            return true;
                        } else{
                            return false;
                        }
                    } else{
                        sl1 = l;
                        while((sl1 = sl1.getRightUp()) != null){
                            if(!sl1.getFigure().getColor().equals(figure.getColor())){
                                if(sl1.getRightDown() != null
                                        && sl1.getRightDown().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }

                        sl2 = l;
                        while((sl2 = sl2.getLeftDown()) != null){
                            if(!sl2.getFigure().getColor().equals(figure.getColor())){
                                if(sl2.getLeftUp() != null
                                        && sl2.getLeftUp().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }
                    }
                }
            }
            if(figure.getRightDownFigure() != null && figure.getRightDownFigure().getThreatened()){
                // if from right down to left up
                //  then get all left upper cell in the loop
                //      and from every check all right up and left down
                //        if found our figure then out of the loop
                //        if found their figure then return true

                // current position has been checked in the canKingFight function,
                // so we just skip it.
                l = figure.getCell();

                while((l = l.getLeftUp()) != null){
                    if(l.getFigure() != null){
                        if(!l.getFigure().getColor().equals(figure.getColor())){
                            return true;
                        } else{
                            return false;
                        }
                    } else{
                        sl1 = l;
                        while((sl1 = sl1.getRightUp()) != null){
                            if(!sl1.getFigure().getColor().equals(figure.getColor())){
                                if(sl1.getRightDown() != null
                                        && sl1.getRightDown().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }

                        sl2 = l;
                        while((sl2 = sl2.getLeftDown()) != null){
                            if(!sl2.getFigure().getColor().equals(figure.getColor())){
                                if(sl2.getLeftUp() != null
                                        && sl2.getLeftUp().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }
                    }
                }
            }
            if(figure.getRightUpFigure() != null && figure.getRightUpFigure().getThreatened()){
                // if from right up to left down
                //  then get all left nether cell in the loop
                //      and from every check all right up and left down
                //        if found our figure then out of the loop
                //        if found their figure then return true

                // current position has been checked in the canKingFight function,
                // so we just skip it.
                l = figure.getCell();

                while((l = l.getLeftDown()) != null){
                    if(l.getFigure() != null){
                        if(!l.getFigure().getColor().equals(figure.getColor())){
                            return true;
                        } else{
                            return false;
                        }
                    } else{
                        sl1 = l;
                        while((sl1 = sl1.getRightDown()) != null){
                            if(!sl1.getFigure().getColor().equals(figure.getColor())){
                                if(sl1.getRightDown() != null
                                        && sl1.getRightDown().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }

                        sl2 = l;
                        while((sl2 = sl2.getLeftUp()) != null){
                            if(!sl2.getFigure().getColor().equals(figure.getColor())){
                                if(sl2.getLeftUp() != null
                                        && sl2.getLeftUp().getFigure() == null){
                                    return true;
                                }
                            } else{
                                break;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private Boolean canKingFight(Figure figure){

        Figure<RussianCoords> f = null;

        Integer vX = null;
        Integer svX = null;

        f = figure.getLeftDownFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                svX = getDistance(f, f.getLeftDownFigure());

                if(svX == null){
                    return true;
                }

                if(!svX.equals(1)){
                    return true;
                }
            }
        }

        f = figure.getLeftUpFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                svX = getDistance(f, f.getLeftUpFigure());

                if(svX == null){
                    return true;
                }

                if(!svX.equals(1)){
                    return true;
                }
            }
        }

        f = figure.getRightDownFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                svX = getDistance(f, f.getRightDownFigure());

                if(svX == null){
                    return true;
                }

                if(!svX.equals(1)){
                   return true;
                }
            }
        }

        f = figure.getRightUpFigure();
        if(f != null && !f.getThreatened()){
            if(!f.getColor().equals(figure.getColor())){
                svX = getDistance(f, f.getRightUpFigure());

                if(svX == null){
                   return true;
                }

                if(!svX.equals(1)){
                   return true;
                }
            }
        }

        return false;
    }

    private Boolean canFigureFight(Figure figure, Boolean afterFight){
        if(figure.getType() == FigureType.CHECKER){
            return this.canCheckerFight(figure);
        } else if(figure.getType() == FigureType.KING){
            if(afterFight){
                return this.canKingFightAfterFight(figure);
            } else {
                return this.canKingFight(figure);
            }
        }

            return false;
    }

    private RussianCoords checkFightAnywhere(FigureColor color){

        if(color == FigureColor.WHITE){
            for(Figure<RussianCoords> f : white.values()){
                if(canFigureFight(f, false)){
                    return f.getCell().getCoords();
                }
            }
        } else if(color == FigureColor.BLACK) {
            for(Figure<RussianCoords> f : black.values()){
                if(canFigureFight(f, false)){
                    return f.getCell().getCoords();
                }
            }
        }

        return null;
    }

    private void isValidCheckerStep(RussianCoords fromCoord, RussianCoords toCoord, FigureColor color) throws CheckersException {
        Integer vX = toCoord.getX() - fromCoord.getX();
        Integer vY = toCoord.getY() - fromCoord.getY();

        //Check step direction (only forward allowed)
        if((vX < 0 && color.equals(FigureColor.WHITE)) || (vX > 0 && color.equals(FigureColor.BLACK))){
            throw new CheckersException(6L, "Invalid step direction (only forward allowed for checker)");
        }

        //Only one step
        if(Math.abs(vX) > 1){
            throw new CheckersException(7L, "Invalid step lenght (only one length step is allowed for checker)");
        }

        //Only diagonally
        if(Math.abs(vX) != Math.abs(vY)){
            throw new CheckersException(8L, "Only diagonally step allowed");
        }

    }

    private void isValidKingStep(RussianCoords fromCoord, RussianCoords toCoord) throws CheckersException {
        Integer vX = toCoord.getX() - fromCoord.getX();
        Integer vY = toCoord.getY() - fromCoord.getY();

        //Only diagonally
        if(!vX.equals(vY)){
            throw new CheckersException(8L, "Only diagonally step allowed");
        }

    }

    @Override
    public Boolean checkStep(String step, FigureColor color) throws CheckersException {

        String[] steps = step.split("-|:");

        //Check is it your figure?
        Figure bf = graph.getFigure(new RussianCoords(steps[0]));

        if(bf == null){
            throw new CheckersException(19L, "Cell is clear");
        }

        if(!bf.getColor().equals(color)){
            throw new CheckersException(13L, "This figure isn't yours");
        }

        // Just a step
        if(step.contains("-")){
            RussianCoords fromCoord = new RussianCoords(steps[0]);
            RussianCoords toCoord = new RussianCoords(steps[1]);

            Figure f = null;

            if(graph.getFigure(toCoord) != null){
                throw new CheckersException(12L, "This cell is occupied");
            }

            RussianCoords fight = checkFightAnywhere(color);

            if(fight != null){
                throw new CheckersException(11L, "Your figure can fight on " + fight.getCheckersNotation());
            }

            if(color.equals(FigureColor.WHITE)){
                f = white.get(fromCoord);
            } else if(color.equals(FigureColor.BLACK)){
                f = black.get(fromCoord);
            }

            if(f == null){
                throw new CheckersException(9L, "There isn't figure on this position");
            }

            if(f.getType().equals(FigureType.CHECKER)){
                isValidCheckerStep(fromCoord, toCoord, color);
            } else if(f.getType().equals(FigureType.KING)){
                isValidKingStep(fromCoord, toCoord);
            }

            graph.moveFigure(fromCoord, toCoord);

            if(color.equals(FigureColor.BLACK)){
                black.put(toCoord, black.get(fromCoord));
                black.remove(fromCoord);
            } else if(color.equals(FigureColor.WHITE)){
                white.put(toCoord, white.get(fromCoord));
                white.remove(fromCoord);
            }

            if(color.equals(FigureColor.BLACK) && toCoord.getX().equals(1)){
                black.get(toCoord).setType(FigureType.KING);
            } else if(color.equals(FigureColor.WHITE) && toCoord.getX().equals(8)){
                white.get(toCoord).setType(FigureType.KING);
            }

            // Step with fight
        } else if(step.contains(":")){

            graph.startTransaction();
            graph.getFigure(new RussianCoords(steps[0])).setFighter(true);

            try{

                for(int i = 1; i < steps.length; i++){
                    RussianCoords fromCoord = new RussianCoords(steps[i-1]);
                    RussianCoords toCoord = new RussianCoords(steps[i]);
                    Figure f = graph.getFigure(fromCoord).getFigureFromDirection(toCoord);

                    if(f == null){
                        throw new CheckersException(18L, "There is no figure for fight");
                    }

                    // all checks should be placed here
                    if(graph.getFigure(toCoord) != null){
                        throw new CheckersException(12L, "This cell is occupied");
                    }

                    if(!graph.getFigure(fromCoord).getFighter()){
                        throw new CheckersException(14L, "You lost step sequence");
                    }

                    if(graph.getFigure(fromCoord).getType().equals(FigureType.CHECKER)){

                        // one step length
                        if(getDistance(graph.getFigure(fromCoord), f) != 1){
                            throw new CheckersException(15L, "Invalid step length (Fight)");
                        }

                        // fight opponent figure
                        if(f.getColor().equals(color)){
                            throw new CheckersException(16L, "You can't step over your figure");
                        }

                        // figure has been threatened already
                        if(f.getThreatened()){
                            throw new CheckersException(17L, "Figure has been threatened already");
                        }

                        // If all steps before performed successfully
                        //then mark figure under fight as threatened
                        f.setThreatened(true);

                    } else if(graph.getFigure(fromCoord).getType().equals(FigureType.KING)){
                        //Check that on the way just one figure
                        if(f.getFigureFromDirection(toCoord) != null){
                            throw new CheckersException(18L, "There is more then one figure on the way");
                        }

                        // fight opponent figure
                        if(f.getColor().equals(color)){
                            throw new CheckersException(16L, "You can't step over your figure");
                        }

                        // figure has been threatened already
                        if(f.getThreatened()){
                            throw new CheckersException(17L, "Figure has been threatened already");
                        }

                        // If all steps before performed successfully
                        //then mark figure under fight as threatened
                        f.setThreatened(true);
                    }

                    graph.moveFigure(fromCoord, toCoord);
                    //if all pre conditions correct in figure step up to last cell in the board then this become king
                    if(color.equals(FigureColor.BLACK) && toCoord.getX().equals(1)){
                        black.get(toCoord).setType(FigureType.KING);
                    } else if(color.equals(FigureColor.WHITE) && toCoord.getX().equals(8)){
                        white.get(toCoord).setType(FigureType.KING);
                    }
                }

            } catch(CheckersException ce){
                graph.rollbackTransaction();
                throw ce;
            }

            if(canFigureFight(graph.getFigure(new RussianCoords(steps[steps.length])), true)){
                throw new CheckersException(19L, "You have another step to fight");
            }

            graph.getFigure(new RussianCoords(steps[steps.length])).setFighter(false);
            graph.commitTransaction();
        }

        return true;
    }
}
