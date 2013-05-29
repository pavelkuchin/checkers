package com.checkers.server.services;

import com.checkers.server.beans.Game;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
@Service
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

    @Override
    public List<Game> getUserGames(Long uuid) {
        List<Game> result = new LinkedList<Game>();

        for(Game g : this.games){
            if(g.getBlack().equals(uuid) || g.getWhite().equals(uuid)){
                result.add(g);
            }
        }

        return result;
    }
}
