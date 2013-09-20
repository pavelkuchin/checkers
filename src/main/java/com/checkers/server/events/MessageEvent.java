package com.checkers.server.events;

import com.checkers.server.beans.Message;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class MessageEvent extends MyEvent{
    private Message message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public MessageEvent(Object source, Message message) {
        super(source, EventName.MESSAGE);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}
