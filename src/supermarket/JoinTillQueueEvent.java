/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;


/**
 * A customer finishes shopping and heads for the till with the shortest queue
 *
 * @author evenal
 */
public class JoinTillQueueEvent extends Event {
    Customer customer;

    public JoinTillQueueEvent(Customer customer) {
        super(EventSim.getClock() + customer.shoppingDuration);
        this.customer = customer;
    }

    @Override
    public Event happen() {
        SuperMarket sm = customer.shop;
        customer.till = sm.getShortestQueueTill();
        System.err.println(this);
        return customer.till.enqueue(this);
    }

    @Override
    public String toString() {
        return String.format("GotoTillEvent{%d %s Till=%s}",
                             getTime(), customer.name, customer.till);
    }
}
