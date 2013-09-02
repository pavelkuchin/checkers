package com.checkers.server.events;

import com.checkers.server.beans.Step;

/**
 *
 *
 *
 * @author Pavel_Kuchin
 */
public class StepEvent extends MyEvent{
    private Step step;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public StepEvent(Object source, Step step) {
        super(source, EventName.STEP);
        this.step = step;
    }

    public Step getStep() {
        return step;
    }
}
