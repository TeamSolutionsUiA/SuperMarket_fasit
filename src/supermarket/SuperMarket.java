/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class SuperMarket extends EventSim {

    public static void main(String[] arts) {
        SuperMarket superMarket = new SuperMarket();
        superMarket.startSim();
    }

    public static final int NUM_CHECKOUTS = 2;
    public static final int NUM_CUSTOMERS = 10;

    Till[] tills;
    List<Customer> customers;
    List<Event> init;

    public SuperMarket() {
        super();
        tills = new Till[NUM_CHECKOUTS];
        for (int i = 0; i < NUM_CHECKOUTS; i++)
            tills[i] = new Till(this, i);
        customers = new ArrayList<>();
        init = new ArrayList<>();
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer c = new Customer(this, i);
            init.add(new StartShoppingEvent(c));
            customers.add(c);
        }
    }

    public void startSim() {
        EventSim sim = EventSim.getInstance();
        sim.setup(init);
        sim.run();
        printStatistics();
    }

    private void printStatistics() {
        int n = 0;
        int sumSTime = 0;
        int maxSTime = Integer.MIN_VALUE;
        int minSTime = Integer.MAX_VALUE;
        int sumQTime = 0;
        int maxQTime = Integer.MIN_VALUE;
        int minQTime = Integer.MAX_VALUE;
        int sumCTime = 0;
        int maxCTime = Integer.MIN_VALUE;
        int minCTime = Integer.MAX_VALUE;

        for (Customer c : customers) {
            n++;
            int t = c.tillQueueDuration;
            sumQTime += t;
            maxQTime = t > maxQTime ? t : maxQTime;
            minQTime = t < minQTime ? t : minQTime;

            t = c.shoppingDuration;
            sumSTime += t;
            maxSTime = t > maxSTime ? t : maxSTime;
            minSTime = t < minSTime ? t : minSTime;

            t = c.checkoutDuration;
            sumCTime += t;
            maxCTime = t > maxCTime ? t : maxCTime;
            minCTime = t < minCTime ? t : minCTime;
        }

        System.err.println("Time spent tatistics for " + n + "customers:");
        System.err.format("\n     %8s %8s %8s\n", "Shopping", "Queueing", "Checkout");
        System.err.format("Min %8d %8d %8d\n", minSTime, minQTime, minCTime);
        System.err.format("Avg %8d %8d %8d\n", (sumSTime / n), (sumQTime / n), (sumCTime / n));
        System.err.format("Max %8d %8d %8d\n", maxSTime, maxQTime, maxCTime);
    }

    public Till getShortestQueueTill() {
        Till tmin = tills[0];
        for (Till t : tills) {
            if (t.getQueueLength() < tmin.getQueueLength())
                tmin = t;
        }
        return tmin;
    }

    @Override
    protected void showState() {
        System.err.format("\nSys.State{ Time %d\nEvent Queue:", getClock());
        for (Event e : getEventQueue()) {
            System.err.format("\n%s", e.toString());
        }
        System.err.println();

        for (Till t : tills) {
            System.err.format("Till %s, queue:\n", t.name);
            for (Customer c : t.queue)
                System.err.println(c.toString());
        }
        System.err.println("}");
    }

}
