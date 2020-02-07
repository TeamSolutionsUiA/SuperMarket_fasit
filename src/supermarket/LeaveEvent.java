/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;


/**
 *
 * @author evenal
 */
public class LeaveEvent extends Event {
    Customer customer;

    public LeaveEvent(int time, Customer customer) {
        super(time);
        this.customer = customer;
    }

    /**
     * This method will be called when a customer has paid the shop and is
     * leaving the shop
     */
    @Override
    public Event happen() {
        System.err.println(this);
//        till.getNextCustomer();
        return null;
    }

    public String toString() {
        return String.format("Leave{%d %s}", getTime(), customer.name);
    }
}
