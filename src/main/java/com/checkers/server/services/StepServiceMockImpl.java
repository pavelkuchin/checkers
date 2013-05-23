package com.checkers.server.services;

import com.checkers.server.beans.Step;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public class StepServiceMockImpl implements StepService{
    List<Step> steps;

    public StepServiceMockImpl(){
        steps = new LinkedList<Step>();
    }

    @Override
    public Step getStep(Long suid) {
        for(Step s : steps){
            if(s.getSuid() == suid){
                return s;
            }
        }
        return null;
    }

    @Override
    public void newStep(Step step) {
        steps.add(step);
    }

    @Override
    public Step getLastStep() {
        Step tmpStep = new Step();

        tmpStep.setCreated(new Date(0));

        for(Step s : steps){
            if(s.getCreated().compareTo(tmpStep.getCreated()) > 0){
                tmpStep = s;
            }
        }
        return tmpStep;
    }
}
