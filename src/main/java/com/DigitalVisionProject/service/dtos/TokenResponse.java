package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.User;

public class TokenResponse {

    private String response;
    private User appUser;

    public TokenResponse(String response, User appUser) {
        this.response = response;
        this.appUser = appUser;
    }

    public TokenResponse(String token) {
        this.response = token;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }
}
