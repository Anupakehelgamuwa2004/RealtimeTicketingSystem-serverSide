package com.cli_ticket.ticketing_system.Controllers;

import com.cli_ticket.ticketing_system.Entity.Configuration;
import com.cli_ticket.ticketing_system.Repository.ConfigurationRepository;
import com.cli_ticket.ticketing_system.cli.Customer;
import com.cli_ticket.ticketing_system.cli.TicketPool;
import com.cli_ticket.ticketing_system.cli.Vendor;
import com.cli_ticket.ticketing_system.dto.TicketConfiguration;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ConfigurationServiceDB {

    private static final long CONFIG_ID = 1L;
    private static final Logger logger = Logger.getLogger(ConfigurationServiceDB.class.getName());

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private TicketPool ticketPool;
    private Thread vendor1Thread, vendor2Thread, customer1Thread, customer2Thread;

    @Transactional
    public void updateConfiguration(TicketConfiguration configDTO) throws IllegalArgumentException {
        // Validate that totalTickets does not exceed maxTicketCapacity
        if (configDTO.getTotalTickets() > configDTO.getMaxTicketCapacity()) {
            String errorMsg = "Total Tickets cannot exceed Max Ticket Capacity.";
            throw new IllegalArgumentException(errorMsg);
        }

        // Load existing configuration or create a new one
        Configuration configuration = configurationRepository.findById(CONFIG_ID).orElse(createDefaultConfiguration());

        // Update fields
        configuration.setMaxTicketCapacity(configDTO.getMaxTicketCapacity());
        configuration.setTotalTickets(configDTO.getTotalTickets());
        configuration.setTicketReleaseRate(configDTO.getTicketReleaseRate());
        configuration.setCustomerRetrievalRate(configDTO.getCustomerRetrievalRate());

        // Save the updated configuration
        configurationRepository.save(configuration);
    }

    @Transactional
    public void startSellingAndBuying(TicketConfiguration configDTO) {
        setupThreads(configDTO);
    }

    public void stopSellingAndBuying() {
        if (vendor1Thread != null && vendor2Thread != null) {
            vendor1Thread.interrupt();
            vendor2Thread.interrupt();
        }
        if (customer1Thread != null && customer2Thread != null) {
            customer1Thread.interrupt();
            customer2Thread.interrupt();
        }
    }

    public Configuration loadConfiguration() {
        Optional<Configuration> configurationOptional = configurationRepository.findById(CONFIG_ID);

        if (configurationOptional.isPresent()) {
            return configurationOptional.get();
        } else {
            Configuration defaultConfig = createDefaultConfiguration();
            saveConfiguration(defaultConfig);
            return defaultConfig;
        }
    }

    private void setupThreads(TicketConfiguration config) {
        ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalTickets(), messagingTemplate);

        vendor1Thread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), "Vendor 1"));
        vendor2Thread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), "Vendor 2"));

        customer1Thread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), "Customer 1"));
        customer2Thread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), "Customer 2"));

        vendor1Thread.start();
        vendor2Thread.start();
        customer1Thread.start();
        customer2Thread.start();
    }

    private Configuration createDefaultConfiguration() {
        Configuration config = new Configuration();
        config.setId(CONFIG_ID);
        config.setMaxTicketCapacity(100);
        config.setTotalTickets(50);
        config.setTicketReleaseRate(1000);
        config.setCustomerRetrievalRate(1000);
        return config;
    }

    private Configuration saveConfiguration(Configuration configuration) {
        configuration.setId(CONFIG_ID);  // Ensure the ID is set to the predefined value
        return configurationRepository.save(configuration);
    }
}
