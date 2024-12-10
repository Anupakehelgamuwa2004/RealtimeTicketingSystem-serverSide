package com.cli_ticket.ticketing_system.cli;

import com.google.gson.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
    private int maxTicketCapacity;
    public static int totalTickets; // Static field
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    public static final String CONFIGURATION_FILE = "configuration.json";

    // Modify to accept a shared Scanner instance
    public void promptUserInput(Scanner scanner) {
        try {
            maxTicketCapacity = validateInput("Enter Max Ticket Capacity", scanner);
            totalTickets = validateInput("Enter Total Tickets", scanner);
            if (totalTickets > maxTicketCapacity) {
                System.out.println("Total Tickets cannot exceed Max Ticket Capacity. \nPlease try again with a less than or equal number to Max Ticket Capacity.");
                totalTickets = validateInput("Total Tickets", scanner);
            }
            ticketReleaseRate = validateInput("Enter Ticket Release Rate (ms)", scanner);
            customerRetrievalRate = validateInput("Enter Customer Retrieval Rate (ms)", scanner);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "An error occurred during input: " + e.getMessage());
        }
        saveConfiguration();
    }

    private int validateInput(String prompt, Scanner scanner) {
        int value;
        while (true) {
            System.out.print(prompt + ": ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Value must be greater than zero. Please try again.");
                }
            } else {
                String invalidInput = scanner.nextLine(); // Consume invalid input
                System.out.println("Invalid input: " + invalidInput + ". Please enter a positive integer.");
            }
        }
        return value;
    }

    // Save the configuration with static field handled manually
    public void saveConfiguration() {
        try (FileWriter fileWriter = new FileWriter(CONFIGURATION_FILE)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("maxTicketCapacity", maxTicketCapacity);
            jsonObject.addProperty("totalTickets", totalTickets); // Manually add static field
            jsonObject.addProperty("ticketReleaseRate", ticketReleaseRate);
            jsonObject.addProperty("customerRetrievalRate", customerRetrievalRate);

            gson.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while saving configuration: " + e.getMessage());
        }
    }

    // Load configuration with static field handled manually
    public void loadConfiguration() {
        File file = new File(CONFIGURATION_FILE);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
                this.maxTicketCapacity = jsonObject.get("maxTicketCapacity").getAsInt();
                totalTickets = jsonObject.get("totalTickets").getAsInt(); // Manually set static field
                this.ticketReleaseRate = jsonObject.get("ticketReleaseRate").getAsInt();
                this.customerRetrievalRate = jsonObject.get("customerRetrievalRate").getAsInt();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "An error occurred while loading configuration: " + e.getMessage());
            }
        } else {
            LOGGER.log(Level.WARNING, "Configuration file not found.");
        }
    }

    // Getters
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    // For deserialization
    public static int getTotalTickets() {
        return totalTickets;
    }
}
