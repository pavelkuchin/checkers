package com.checkers.server.beans;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class ListenObjects {
    private Game game;
    private Step step;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
