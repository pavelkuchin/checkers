package com.checkers.server.events;

import org.springframework.context.ApplicationEvent;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class MyEvent extends ApplicationEvent{

    public enum EventName{
        GAME, STEP, USER, MESSAGE
    }


    private final EventName name;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public MyEvent(Object source, EventName name) {
        super(source);
        this.name = name;
    }

    public EventName getName(){
        return this.name;
    }
}
