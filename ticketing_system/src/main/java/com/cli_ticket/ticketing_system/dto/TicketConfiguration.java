package com.cli_ticket.ticketing_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TicketConfiguration {

    @NotNull(message = "Max Ticket Capacity is required.")
    @Min(value = 1, message = "Max Ticket Capacity must be greater than zero.")
    private Integer maxTicketCapacity;

    @NotNull(message = "Total Tickets is required.")
    @Min(value = 1, message = "Total Tickets must be greater than zero.")
    private Integer totalTickets;

    @NotNull(message = "Ticket Release Rate is required.")
    @Min(value = 1, message = "Ticket Release Rate must be greater than zero.")
    private Integer ticketReleaseRate;

    @NotNull(message = "Customer Retrieval Rate is required.")
    @Min(value = 1, message = "Customer Retrieval Rate must be greater than zero.")
    private Integer customerRetrievalRate;

    // Default constructor for deserialization
    public TicketConfiguration() {}

    // Constructor with parameters for convenience
    public TicketConfiguration(Integer maxTicketCapacity, Integer totalTickets, Integer ticketReleaseRate, Integer customerRetrievalRate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Getters and Setters
    public Integer getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(Integer maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Integer getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(Integer ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public Integer getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(Integer customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
}
