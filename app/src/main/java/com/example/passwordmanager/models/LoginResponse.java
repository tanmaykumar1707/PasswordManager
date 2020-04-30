package com.example.passwordmanager.models;

public class LoginResponse {
    private String userid;
    private boolean status;
    private String Message;

    public LoginResponse(String id_user, boolean status, String message) {
        this.userid = id_user;
        this.status = status;
        this.Message = message;
    }



    public String getId_user() {
        return userid;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return Message;
    }
}
