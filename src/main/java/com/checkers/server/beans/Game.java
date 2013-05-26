package com.checkers.server.beans;

import java.util.Date;

/**
 * Game entity.
 * Related with 'User' entity (white, black = UUID)
 *
 * @author Pavel_Kuchin
 */

public class Game {
    // Game unique identifier
    private Long    gauid;

    // Game public name
    private String  name;
    // Game description (too public)
    private String  description;

    /**
     * Game type:
     *  online - During one session (minutes or hours)
     *  offline - During many sessions (days or months)
     */
    private String  type;

    /**
     * Board type:
     * 8x8   - Russian checkers
     * 10x10 - Worldwide checkers
     */
    private String  board;

    /**
     * Game state:
     *  open - game has been opened. Find black player.
     *  game - game in process
     *  close - game closed (win or dead heat)
     */
    private String  state;

    // White player uuid
    private Long    white;
    // Black player uuid
    private Long    black;

    // Date of game creation
    private Date    created;
    // Date of game modification
    private Date    modified;
    // Date of last step
    private Date    lastStep;

    public Game(){
    }

    public Long getGauid() {
        return gauid;
    }

    public void setGauid(Long gauid) {
        this.gauid = gauid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getWhite() {
        return white;
    }

    public void setWhite(Long white) {
        this.white = white;
    }

    public Long getBlack() {
        return black;
    }

    public void setBlack(Long black) {
        this.black = black;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastStep() {
        return lastStep;
    }

    public void setLastLogin(Date lastStep) {
        this.lastStep = lastStep;
    }
}
