package com.checkers.server.beans;

import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */

public class Game {
    private Long    gauid;

    private String  name;
    private String  description;

    private String  type;
    private String  board;
    private String  state;

    private long    white;
    private long    black;

    private Date    created;
    private Date    modified;
    private Date    lastLogin;

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

    public long getWhite() {
        return white;
    }

    public void setWhite(long white) {
        this.white = white;
    }

    public long getBlack() {
        return black;
    }

    public void setBlack(long black) {
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
