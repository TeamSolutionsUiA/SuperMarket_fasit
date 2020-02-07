/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;


/**
 * This type of event is created when customers join the queue. It marks the end
 * of the period where a customer is passively waiting in the queue. The
 * customer will now enter a checkout process with the till.
 *
 * @author evenal
 */
public class StartCheckoutEvent extends Event {
    Customer customer;

    public StartCheckoutEvent(Customer customer, int time) {
        super(time);
        this.customer = customer;
    }

    /**
     * this method will be called when a customer has moved to the, front of the
     * queue, and is just about to start checking out
     */
    @Override
    public Event happen() {
        SuperMarket shop = customer.shop;
//        Till till = customer.till;
        System.err.println(this);
        return new LeaveEvent(time + customer.checkoutDuration, customer);
    }

    public String toString() {
        return String.format("StartCheckout{%d %s %s %d",
                             getTime(),
                             customer.name,
                             customer.till.name,
                             customer.checkoutDuration);
    }
}
