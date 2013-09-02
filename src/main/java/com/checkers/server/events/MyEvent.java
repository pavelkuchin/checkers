package com.checkers.server.events;

import org.springframework.context.ApplicationEvent;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class MyEvent extends ApplicationEvent{

    private final String name;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public MyEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
