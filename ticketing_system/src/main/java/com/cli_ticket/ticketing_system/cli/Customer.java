package com.cli_ticket.ticketing_system.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final String customerName;
    private static final Logger logger = Logger.getLogger(Customer.class.getName());

    public Customer(TicketPool ticketPool, int customerRetrievalRate, String customerName) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boolean ticketRetrieved = ticketPool.retrieveTicket(customerName);
                if (!ticketRetrieved) {
                    break;
                }
                Thread.sleep(customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, customerName + " has been interrupted.");
        }
        logger.log(Level.WARNING, customerName + " has finished purchasing tickets.");
    }
}
