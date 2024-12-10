package com.cli_ticket.ticketing_system.util;

public class Message {
    private String content;
    private String sender;

    public Message() {
    }

    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
