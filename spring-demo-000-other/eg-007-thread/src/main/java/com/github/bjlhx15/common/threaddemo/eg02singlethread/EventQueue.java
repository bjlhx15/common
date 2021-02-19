package com.github.bjlhx15.common.threaddemo.eg02singlethread;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @author lihongxu6
 * @version 1.0
 * @className EventQueue
 * @description TODO
 * @date 2021-02-18 14:33
 */
public class EventQueue {
    private final int max;

    static class Event {
    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                console(" the queue is full");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                console("the queue is empty.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message) {
        System.out.println("线程名：" + currentThread().getName() + ":" + message);
    }
}
