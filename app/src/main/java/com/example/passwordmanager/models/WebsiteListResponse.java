package com.example.passwordmanager.models;

public class WebsiteListResponse {
    private String web_name;
    private String web_email;
    private String web_password;

    public WebsiteListResponse(String web_name, String web_email, String web_password) {
        this.web_name = web_name;
        this.web_email = web_email;
        this.web_password = web_password;
    }

    public String getWeb_name() {
        return web_name;
    }

    public String getWeb_email() {
        return web_email;
    }

    public String getWeb_password() {
        return web_password;
    }
}
