package com.checkers.server.beans;

import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public class Step {
    // Step unique identifier
    private Long    suid;
    // Game unique identifier
    private Long    gauid;
    // User unique identifier
    private Long    uuid;
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

    public Long getGauid(){
        return gauid;
    }

    public void setGauid(Long gauid){
        this.gauid = gauid;
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

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
}


