package com.example.passwordmanager.models;

public class Email_idListResponse {
    private int id;
    private String domain;
    private String password;
    private String email_id;
    private String updated;

    public Email_idListResponse(int id, String domain, String password, String email_id, String updated) {
        this.id = id;
        this.domain = domain;
        this.password = password;
        this.email_id = email_id;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getUpdated() {
        return updated;
    }
}
