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
}


