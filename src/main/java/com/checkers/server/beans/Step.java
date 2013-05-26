package com.checkers.server.beans;

import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public class Step {
    private Long    suid;
    private Long    gauid;
    private Long    uuid;
    private String  step;
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


