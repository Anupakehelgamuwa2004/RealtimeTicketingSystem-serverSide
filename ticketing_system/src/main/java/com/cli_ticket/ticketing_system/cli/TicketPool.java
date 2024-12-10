package com.cli_ticket.ticketing_system.cli;

import com.cli_ticket.ticketing_system.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.logging.Logger;

public class TicketPool {
    private final int maxTicketCapacity; // Total tickets to sell
    private final int totalTickets;    // Max tickets in the system at once
    private int currentTickets = 0;      // Tickets currently in the system
    private int totalTicketsAdded = 0;   // Total tickets added to the system
    private int totalTicketsSold = 0;    // Total tickets sold
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    private SimpMessagingTemplate template;


    public TicketPool(int maxTicketCapacity, int totalTickets, SimpMessagingTemplate template) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.template = template;
    }


    // Vendor adds tickets to the system
    public synchronized void addTickets(int numTickets, String vendorName) throws InterruptedException {
        while (totalTicketsAdded >= maxTicketCapacity) {
            logger.info(vendorName + " is waiting to add tickets (capacity reached)...");
            wait(); // Wait if the max capacity is reached
        }

        int availableCapacity = totalTickets - currentTickets;
        int ticketsRemainingToAdd = maxTicketCapacity - totalTicketsAdded;
        int ticketsToAdd = Math.min(numTickets, availableCapacity);
        ticketsToAdd = Math.min(ticketsToAdd, ticketsRemainingToAdd);

        if (ticketsToAdd <= 0) {
            return;
        }

        currentTickets += ticketsToAdd;
        totalTicketsAdded += ticketsToAdd;

        template.convertAndSend("/topic/tickets", new Message("Vendor " + vendorName, "added " + ticketsToAdd + " tickets."));
        logger.info(vendorName + " added " + ticketsToAdd + " tickets. Current available: " + currentTickets);

        notifyAll(); // Notify customers that tickets are available
    }

    // Customer retrieves a ticket from the system
    public synchronized boolean retrieveTicket(String customerName) throws InterruptedException {
        while (currentTickets == 0) {
            if (totalTicketsSold >= maxTicketCapacity && totalTicketsAdded >= maxTicketCapacity) {
                return false;
            }
            logger.info(customerName + " is waiting to purchase tickets...");
            wait();
        }

        currentTickets--;
        totalTicketsSold++;
        template.convertAndSend("/topic/tickets", new Message("Customer " + customerName, "purchased a ticket. Current tickets count in the system: " + currentTickets));
        logger.info(customerName + " purchased a ticket. Current tickets count in the system: " + currentTickets);

        notifyAll(); // Notify vendors that space is available

        return true;
    }

    // Check if all tickets have been sold
    public synchronized boolean allTicketsSold() {
        return totalTicketsSold >= maxTicketCapacity;
    }
}
