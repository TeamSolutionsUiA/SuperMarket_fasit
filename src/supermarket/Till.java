/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class Till {
    // amount of time per prouct (to scan barcode)
    public static final int PROD_DURATION = 1;
    // amount of time to pay
    public static final int PAY_DURATION = 3;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd

    SuperMarket shop;
    String name;
    boolean idle;
    List<Customer> queue;

    public Till(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Till" + i;
        queue = new LinkedList<Customer>();
    }

    /**
     * This method is called to handle JoinTillQueueEvents.
     *
     * @param cust
     * @return
     */
    public Event enqueue(JoinTillQueueEvent e) {
        Customer cust = e.customer;

        if (queue.isEmpty()) {
            queue.add(cust);
            cust.tillQueueDuration = 0;
            return new LeaveEvent(e.time + cust.checkoutDuration,
                                  cust);
        }

        else {
            Customer last = queue.get(queue.size() - 1);
            cust.startCheckoutTime = last.leaveTime + cust.checkoutDuration;
            queue.add(cust);
            cust.tillQueueDuration = cust.startCheckoutTime - cust.startTillQueueTime;
            assert cust.tillQueueDuration > 0;
            assert cust.leaveTime > cust.startCheckoutTime;
            assert cust.startCheckoutTime > cust.startTillQueueTime;
            return new StartCheckoutEvent(cust, cust.startCheckoutTime);
        }
    }

    public int getQueueLength() {
        return queue.size();
    }
}
