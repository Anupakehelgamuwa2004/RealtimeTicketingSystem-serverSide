package com.cli_ticket.ticketing_system.dto;

public class ResponseMessage {
    private String message;

    public ResponseMessage() {}

    public ResponseMessage(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
