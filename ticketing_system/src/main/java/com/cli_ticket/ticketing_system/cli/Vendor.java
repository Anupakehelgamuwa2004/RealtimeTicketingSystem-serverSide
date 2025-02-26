package com.cli_ticket.ticketing_system.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int numTicketsToAddEachTime = 10; // Vendor adds tickets in batches of 10
    private final String vendorName;
    private static final Logger logger = Logger.getLogger(Vendor.class.getName());

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, String vendorName) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                logger.info(vendorName + " trying to add tickets...");
                ticketPool.addTickets(numTicketsToAddEachTime, vendorName); // Add tickets to the pool
                Thread.sleep(ticketReleaseRate); // Wait before adding more tickets
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, vendorName + " has been interrupted.");
        }
        logger.log(Level.WARNING, vendorName + " has finished adding tickets.");
    }
}
