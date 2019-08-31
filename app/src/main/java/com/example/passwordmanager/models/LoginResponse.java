package com.example.passwordmanager.models;

public class LoginResponse {
    private String id_user;
    private boolean status;
    private String Message;

    public LoginResponse(String id_user, boolean status, String message) {
        this.id_user = id_user;
        this.status = status;
        this.Message = message;
    }



    public String getId_user() {
        return id_user;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return Message;
    }
}
