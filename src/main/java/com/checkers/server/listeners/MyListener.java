package com.checkers.server.listeners;

import com.checkers.server.events.MyEvent;
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

    ConcurrentMap<String, Object> events = new ConcurrentHashMap<String, Object>();

    @Override
    public void onApplicationEvent(MyEvent event) {
        Object se;

        createEventIfNoExist(event.getName());
        se = events.get(event.getName());
        synchronized (se){
            se.notifyAll();
        }
    }

    private synchronized void createEventIfNoExist(String eventName){
        if(!events.containsKey(eventName)){
            events.put(eventName, new Object());
        }
    }

    public void waitEvent(String eventName) throws InterruptedException {
        Object event;

        createEventIfNoExist(eventName);
        event = events.get(eventName);
        synchronized (event){
            event.wait();
        }
    }
}
