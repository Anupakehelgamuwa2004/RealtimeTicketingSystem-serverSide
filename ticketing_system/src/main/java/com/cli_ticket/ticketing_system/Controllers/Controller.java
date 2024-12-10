package com.cli_ticket.ticketing_system.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private  ConfigurationServiceDB configurationServiceDB;
    private final SimpMessagingTemplate messagingTemplate;

    public Controller(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(com.cli_ticket.ticketing_system.util.Message message) {
        // Send the message to the "/topic/messages" destination
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

}
