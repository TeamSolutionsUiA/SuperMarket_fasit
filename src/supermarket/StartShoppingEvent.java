/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;


/**
 * Events of this type represents customers entering the shop. These events will
 * happen at the customers startShoppingTime
 *
 * @author evenal
 */
public class StartShoppingEvent extends Event {
    Customer customer;

    public StartShoppingEvent(Customer customer) {
        super(customer.startShoppingTime);
        this.customer = customer;
    }

    @Override
    public Event happen() {
        System.err.println(this);
        return new JoinTillQueueEvent(customer);
    }

    @Override
    public String toString() {
        return String.format("BeginShoppingEvent{%d, %s, shopDur=%d}",
                             getTime(), customer.name, customer.shoppingDuration);
    }
}
