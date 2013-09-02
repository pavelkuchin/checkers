package com.checkers.server.listeners;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.beans.User;
import com.checkers.server.events.MyEvent;
import com.checkers.server.events.StepEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */

@Component
public class MyListener implements ApplicationListener<MyEvent> {
    private class Syncer{
        private MyEvent event;

        public MyEvent getEvent() {
            return event;
        }

        public void setEvent(MyEvent event) {
            this.event = event;
        }
    }

    ConcurrentMap<MyEvent.EventName, Syncer> events = new ConcurrentHashMap<MyEvent.EventName, Syncer>();

    @Override
    public void onApplicationEvent(MyEvent event) {
        Syncer se;

        createEventIfNoExist(event.getName());
        se = events.get(event.getName());
        synchronized (se){
            se.notifyAll();
            se.setEvent(event);
        }
    }

    private synchronized void createEventIfNoExist(MyEvent.EventName eventName){
        if(!events.containsKey(eventName)){
            events.put(eventName, new Syncer());
        }
    }

    public MyEvent waitEvent(MyEvent.EventName eventName) throws InterruptedException {
        Syncer syncer;

        createEventIfNoExist(eventName);
        syncer = events.get(eventName);
        synchronized (syncer){
            syncer.wait();
                return syncer.getEvent();
        }
    }

    public <E> E waitEvent(Class<E> type) throws InterruptedException {
        if (StepEvent.class.equals(type)) {
            return type.cast(waitEvent(MyEvent.EventName.STEP));
        }/* else if (GameEvent.class.equals(type)) {
            return type.cast(waitEvent(MyEvent.EventName.GAME));
        } else if (UserEvent.class.equals(type)) {
            return type.cast(waitEvent(MyEvent.EventName.USER));
        }  */

        return null;
    }
}
