package com.checkers.server.beans;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */
@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    suid;     // Step unique identifier

    private Long    gauid;   // Game unique identifier

    private Long    uuid;    // User unique identifier

    @ManyToOne
    private Game    game;    // Game object

    @ManyToOne
    private User    user;    // User object
    /**
     * Step in checkers notation
     * 2b-3b - Russian (8x8)
     * 1-10  - Worldwide (10x10)
     */
    private String  step;
    // Date of creation
    private Date    created;

    public Step(){

    }

    public Long getSuid(){
        return suid;
    }

    public void setSuid(Long suid){
        this.suid = suid;
    }

    public String getStep(){
        return this.step;
    }

    public void setStep(String step){
        this.step = step;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGauid(Long gauid) {
        this.gauid = gauid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getGauid() {
        return gauid;
    }

    public Long getUuid() {
        return uuid;
    }

}


