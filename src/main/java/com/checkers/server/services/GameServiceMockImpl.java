package com.checkers.server.services;

import com.checkers.server.beans.Game;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public class GameServiceMockImpl implements GameService {
    private List<Game> games;

    public GameServiceMockImpl(){
        games = new LinkedList<Game>();
    }

    @Override
    public Game getGame(Long gauid) {
        for(Game g : games){
            if(g.getGauid() == gauid){
                return g;
            }
        }
            return null;
    }

    @Override
    public void newGame(Game game) {
        games.add(game);
    }

    @Override
    public List<Game> getGames() {
        return games;
    }
}
