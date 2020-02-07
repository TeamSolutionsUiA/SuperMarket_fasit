/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsim;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


/**
 * The core class of the discrete event simulation
 *
 * @author evenal
 */
public abstract class EventSim {
    /**
     * The one and only instance, i.e. this is a singleton class
     */
    private static EventSim theSim;

    /* The queue of events - those that happen earliest first */
    PriorityQueue<Event> eventQueue;

    /**
     * The "current" time
     */
    int clock;
    Random random;

    public static EventSim getInstance() {
        return theSim;
    }

    public static int getClock() {
        return theSim.clock;
    }

    /**
     * Draw a random number in the interval min-max
     *
     * @param min
     * @param max
     * @return
     */
    public static int nextInt(int min, int max) {
        return min + theSim.random.nextInt(max - min);
    }

    public EventSim() {
        eventQueue = new PriorityQueue<>(new EventTimeComparator());
        random = new Random(42);
        theSim = this;
    }

    /**
     * Prepare the simulation by adding a list of "start" events
     *
     * @param initialEvents
     */
    public void setup(List<Event> initialEvents) {
        for (Event e : initialEvents)
            eventQueue.add(e);
    }

    public void addEvent(Event e) {
        if (null == e)
            return;
        eventQueue.add(e);
    }

    protected abstract void showState();

    protected PriorityQueue<Event> getEventQueue() {
        return eventQueue;
    }

    /**
     * Run the simulation. Advances the time (clock) to the time when the next
     * event happens, executes the next event, and repeats until the event queue
     * is empty. You can also rewrite this to stop at a predetermined time (e.g.
     * closing time)
     */
    public void run() {
        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            clock = e.getTime();
            addEvent(e.happen());

            showState();
        }
    }
}
