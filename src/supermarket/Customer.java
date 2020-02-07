/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.EventSim;


/**
 *
 * @author evenal
 */
public class Customer {
    // customer will pick a random number of products between these two values
    public static final int MAX_PRODUCTS = 100;
    public static final int MIN_PRODUCTS = 1;

    // customer will spend ranom amount of time between these values before
    // going to check out
    public static final int MAX_SHOP_TIME = 50;
    public static final int MIN_SHOP_TIME = 1;

    public final SuperMarket shop;
    public Till till;
    String name;

    int startShoppingTime;      // time when customer enters shop
    int shoppingDuration;   // length of time spent shopping
    int startTillQueueTime;       // time when customer goes to till
    int tillQueueDuration;      // time spent in the checkout queue
    int startCheckoutTime;  // time when the till start serving the customer
    int checkoutDuration;   // time spent at till;
    int leaveTime;          // time when checkout is complete and cust leaves

    int numProducts;

    public Customer(SuperMarket shop, int i) {
        this.shop = shop;
        name = "Cust" + i;
        startShoppingTime = i;
        numProducts = EventSim.nextInt(MIN_PRODUCTS, MAX_PRODUCTS);
        shoppingDuration = EventSim.nextInt(MIN_SHOP_TIME, MAX_SHOP_TIME);
        startTillQueueTime = startShoppingTime + shoppingDuration;
        checkoutDuration = Till.PAY_DURATION
                + Till.PROD_DURATION * numProducts;
    }

    public String toString() {
        return String.format("Customer{%s, Shop %d %d, Que %d %d, Chkout %d %d}",
                             name, startShoppingTime, shoppingDuration,
                             startTillQueueTime, tillQueueDuration,
                             startCheckoutTime, checkoutDuration);

    }
}
