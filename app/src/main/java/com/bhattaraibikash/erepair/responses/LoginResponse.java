package com.bhattaraibikash.erepair.responses;

public class LoginResponse {
    private String status;
    private String token;

    public LoginResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
