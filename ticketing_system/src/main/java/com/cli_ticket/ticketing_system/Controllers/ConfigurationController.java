package com.cli_ticket.ticketing_system.Controllers;

import com.cli_ticket.ticketing_system.Entity.Configuration;
import com.cli_ticket.ticketing_system.dto.ResponseMessage;
import com.cli_ticket.ticketing_system.dto.TicketConfiguration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

    @Autowired
    private ConfigurationServiceDB configurationServiceDB;

    @PostMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateConfiguration(@Valid @RequestBody TicketConfiguration ticketConfiguration) {
        try {
            configurationServiceDB.updateConfiguration(ticketConfiguration);
            return ResponseEntity.ok(new ResponseMessage("Configuration has been updated successfully!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<TicketConfiguration> getCurrentConfiguration() {
        Configuration config = configurationServiceDB.loadConfiguration();
        TicketConfiguration configDTO = new TicketConfiguration(
                config.getMaxTicketCapacity(),
                config.getTotalTickets(),
                config.getTicketReleaseRate(),
                config.getCustomerRetrievalRate()
        );
        return ResponseEntity.ok(configDTO);
    }

    @PostMapping("/start")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> startThreads(@RequestBody TicketConfiguration ticketConfiguration) {
        try {
            configurationServiceDB.startSellingAndBuying(ticketConfiguration);
            return ResponseEntity.ok(new ResponseMessage("Selling and buying threads started."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error starting threads."));
        }
    }

    @PostMapping("/stop")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> stopThreads() {
        try {
            configurationServiceDB.stopSellingAndBuying();
            return ResponseEntity.ok(new ResponseMessage("Selling and buying threads stopped."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error stopping threads."));
        }
    }
}