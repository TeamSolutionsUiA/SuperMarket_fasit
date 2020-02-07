/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsim;

import java.util.Comparator;


/**
 * This is used by the priority queue to compare events
 *
 * @author evenal
 */
public class EventTimeComparator
        implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        return e1.time - e2.time;
    }

}
