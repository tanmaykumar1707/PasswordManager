package com.example.passwordmanager.models;

public class EmailidAdd_response {
    private boolean status;
    private String Message;

    public EmailidAdd_response(boolean status, String message) {
        this.status = status;
        Message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return Message;
    }
}
